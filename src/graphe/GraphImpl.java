package graphe;
import java.util.*;
public class GraphImpl<T> implements Graph.graph<T> {
    /*
    * Structure de donnee prenant en cle l'identifiant des sommets et comme valeur la liste des arcs sortant de chaque sommet
     */
    private Map<T, List<Arc<T>>> listeAdjacence;
    public GraphImpl(){
        listeAdjacence = new HashMap<>();
    }

    //ajouterSommet
    public void addNode(T s){
        listeAdjacence.putIfAbsent(s, new ArrayList<>());
    }

    /*ajouter arc
    * parametres source destination et valuation
    *
     */
    public void addEdge(T source, T destination, int valuation){
        addNode(source);
        addNode(destination);
        listeAdjacence.get(source).add(new Arc<>(valuation,destination));

    }

    public boolean checkIfNodeExistInGraph(T node){
        return listeAdjacence.containsKey(node);
    }

    @Override
    public List<Arc<T>> getSucc(T s) {
        return listeAdjacence.getOrDefault(s, Collections.emptyList());
    }
    // au format "A-B(5), A-C(10), B-C(3), C-D(8), E:";
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (T sommet : listeAdjacence.keySet()){
            sb.append(sommet).append("-");
            for (Arc<T> arc : getSucc(sommet)){
                if (getSucc(sommet) == null)
                    sb.append(" ");
                else
                    sb.append(arc.dst()).append("(").append(arc.val()).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    // affiche le graphe
    public void afficherGraphe(){
        System.out.println(toString());
    }

}
