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

		// boucle pour le djikstra
		while (true){
			//On essaie de trouvé le sommet non visité avec la plus petite distance
			T actuel = null;
			int distanceMin = Integer.MAX_VALUE;

			for(Map.Entry<T, Integer> entrer : distances.entrySet()){
				T sommet = entrer.getKey();
				int distance = entrer.getValue();

				if(!marques.contains(sommet) && distance < distanceMin){
					actuel = sommet;
					distanceMin = distance;
				}
			}
			// Si tous sommets sont visité on degage de la boucle
			if(actuel == null){
				break; // M.Poitrenaud me tuerait pour ça
			}

			//On dit que le sommet est marque parce qu'on est déjà passée dessu si j'ai bien comprit ce que Ilyes à fait comme structure de données
			//De toute façon c'est la seule HashSet qui sert a stocker si j'ai bien comprie (?)
			marques.add(actuel);

			//Maintenant on met a jour avec les distance adjacent
			for(Graph.graph.Arc<T> arc : g.getSucc(actuel)){
				T voisin = arc.dst();
				if(arc.val() < 0)
					throw new IllegalArgumentException("Dans dijkstra on ne prend pas de valuation négatif !");
				int nouvelleDistance = distanceMin + arc.val();

				//On voit si la new distance est mieux (Ou cas ou j'oublie le Integer.MAX c'est le signe infini qu'on mettait pour le tableau)
				if(nouvelleDistance < distances.getOrDefault(voisin,Integer.MAX_VALUE)){
					distances.put(voisin,nouvelleDistance);
					predecesseurs.put(voisin,actuel);
				}
			}
		}
		return new Distances<>(distances,predecesseurs);
	}

}
