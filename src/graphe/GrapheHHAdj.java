package graphe;

import java.util.*;

public class GrapheHHAdj implements VarGraph {

	/*
	 * Structure de donnee prenant en cle l'identifiant des sommets et comme valeur la liste des arcs sortant de chaque sommet
	 */
	private Map<String, List<Graph.graph.Arc<String>>> listeAdjacence;

	public GrapheHHAdj() {
		listeAdjacence = new HashMap<>();
	}

	@Override
	public List<Graph.graph.Arc<String>> getSucc(String s) {
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
		listeAdjacence.get(source).add(new Graph.graph.Arc<>(valeur, destination));
	}

	public boolean verifierExistenceSommet(String noeud) {
		return listeAdjacence.containsKey(noeud);
	}
	public Set<String> getSommets() {
		return listeAdjacence.keySet();
	}

	@Override
	public String toString() {
		if (listeAdjacence.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (String sommet : listeAdjacence.keySet()) {
			List<Graph.graph.Arc<String>> arcs = getSucc(sommet);
			sb.append(sommet).append("-");

			if (!arcs.isEmpty()) {
				for (int i = 0; i < arcs.size(); i++) {
					Graph.graph.Arc<String> arc = arcs.get(i);
					sb.append(arc.dst()).append("(").append(arc.val()).append(")");
					if (i < arcs.size() - 1) {
						sb.append(" ");
					}
				}
			}

			sb.append("\n");
		}
		return sb.toString();
	}

	public void afficherGraphe() {
		System.out.println(toString());
	}
}