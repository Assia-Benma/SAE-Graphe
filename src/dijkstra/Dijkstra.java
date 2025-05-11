package dijkstra;

import graphe.Graph;
import graphe.ShortestPath;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra<T> implements ShortestPath<T> {
	/**
	 * Calcul des plus courts chemins.
	 *                 Identifiant des sommets. Le type doit être "hachable".
	 * @param g        Le graphe pour lequel le calcul est demandé.
	 * @param src      Le sommet de {@code g} à partir duquel les plus courts chemins
	 *                 sont demandés.
	 * @param animator L'animateur du parcours. Il est invoqué chaque fois qu'une
	 *                 distance est connue.
	 * @return Une instance de {@code Resultat<T>} contenant tous les résultats.
	 */
	@Override
	public Distances<T> compute(Graph.graph<T> g, T src, Animator<T> animator) throws IllegalArgumentException {
		// Verification si le sommet source existe
		if (g.getSucc(src) == null) {
			throw new IllegalArgumentException("Le sommet source n'existe pas dans le graphe.");
		}
		// Initialisation des structures de données
		Set<T> marques = new HashSet<>();
		Map<T, Integer> distances = new HashMap<>();
		Map<T, T> predecesseurs = new HashMap<>();

		// Initialisation
		marques.add(src); 				// M = {src}
		distances.put(src,0); 			// d[src] = 0
		predecesseurs.put(src, null);   // p[src] = null

		//pour tout les autres sommets

		return new Distances<>(distances,predecesseurs);
	}

}
