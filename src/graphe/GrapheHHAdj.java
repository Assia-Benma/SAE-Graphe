package graphe;

import java.util.*;

public class GrapheHHAdj implements VarGraph {

	/*
	 * Structure de donnee prenant en cle l'identifiant des sommets et comme valeur la liste des arcs sortant de chaque sommet
	 */
	private Map<String, List<Arc<String>>> listeAdjacence;

	public GrapheHHAdj() {
		listeAdjacence = new HashMap<>();
	}

	@Override
	public List<Arc<String>> getSucc(String s) {
		return listeAdjacence.getOrDefault(s, Collections.emptyList());
	}

	@Override
	public void ajouterSommet(String noeud) {
		listeAdjacence.putIfAbsent(noeud, new ArrayList<>());
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		ajouterSommet(source);
		ajouterSommet(destination);
		listeAdjacence.get(source).add(new Arc<>(valeur, destination));
	}

	public boolean verifierExistenceSommet(String noeud) {
		return listeAdjacence.containsKey(noeud);
	}

	// au format "A-B(5), A-C(10), B-C(3), C-D(8), E:"
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String sommet : listeAdjacence.keySet()) {
			sb.append(sommet).append("-");
			for (Arc<String> arc : getSucc(sommet)) {
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
	public void afficherGraphe() {
		System.out.println(toString());
	}
}