# SAE Graphe - Rapport de Projet

## Membres de l’équipe

Groupe 105
- Kunborrémi Hing  
- Assia Benmakhlouf
- Ilyes Salhi
- Andy Morino-ros

---

## Fonctionnement du projet

- **Ce qui marche :**
  - Le chargement des labyrinthes via les fichiers `.maze` fonctionne correctement.
  - L’algorithme de Dijkstra est opérationnel et s’adapte aux objets de type `Maze` via la classe `GraphMaze`.
  - L’adaptateur `GraphMaze` a bien été codé pour faire le lien entre les labyrinthes et le graphe utilisé par Dijkstra.
  - Le programme passe les tests de Checker fournis sur Moodle avec tous les labyrinthes du dossier `bench`.

- **Ce qui ne marche pas / limitations éventuelles :**
  - L’animation dans Animation.java ne fonctionne pas pour les très grands labyrinthes.
  - Certains labyrinthes non conformes au format attendu peuvent poser problème.
