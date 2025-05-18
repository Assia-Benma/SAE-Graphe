package dijkstra;

import graph.Graph;
import graph.ShortestPath;

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
		Set<T> marques = new HashSet<>();  // Ensemble des sommets dont la distance est définitivement connue
		Map<T, Integer> distances = new HashMap<>();  // Distances depuis la source
		Map<T, T> predecesseurs = new HashMap<>();   // Prédécesseurs dans le plus court chemin

		// Initialisation de la source
		distances.put(src, 0);
		predecesseurs.put(src, null);

		// Vérification des valuations négatives dans les arcs sortants de tous les sommets accessibles
		Set<T> sommetsPotentiels = new HashSet<>();
		sommetsPotentiels.add(src);

		while (!sommetsPotentiels.isEmpty()) {
			//On essaie de trouvé le sommet non visité avec la plus petite distance
			T actuel = null;
			int distanceMin = Integer.MAX_VALUE;

			for (T sommet : sommetsPotentiels) {
				Integer distance = distances.get(sommet);
				if (distance != null && distance < distanceMin) {
					actuel = sommet;
					distanceMin = distance;
				}
			}

			// Si tous sommets sont visité on degage de la boucle
			if (actuel == null) {
				break; // M.Poitrenaud me tuerait pour ça
			}

			//On dit que le sommet est marque parce qu'on est déjà passée dessu si j'ai bien comprit ce que Ilyes à fait comme structure de données
			//De toute façon c'est la seule HashSet qui sert a stocker si j'ai bien comprie (?)
			marques.add(actuel);
			sommetsPotentiels.remove(actuel);

			// Notification pour l'animation
			animator.accept(actuel, distances.get(actuel));

			//Maintenant on met a jour avec les distance adjacent
			for (Graph.graph.Arc<T> arc : g.getSucc(actuel)) {
				T voisin = arc.dst();

				// Vérification de valuation négative
				if (arc.val() < 0) {
					throw new IllegalArgumentException("Valuation négative détectée !");
				}

				int nouvelleDistance = distances.get(actuel) + arc.val();

				//On voit si la new distance est mieux (Ou cas ou j'oublie le Integer.MAX c'est le signe infini qu'on mettait pour le tableau)
				if (!distances.containsKey(voisin) || nouvelleDistance < distances.get(voisin)) {
					distances.put(voisin, nouvelleDistance);
					predecesseurs.put(voisin, actuel);

					// Ajouter le voisin aux sommets potentiels s'il n'est pas marqué
					if (!marques.contains(voisin)) {
						sommetsPotentiels.add(voisin);
					}
				}
			}
		}

		return new Distances<>(distances, predecesseurs);
	}
}