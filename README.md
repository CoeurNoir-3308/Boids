# Boids
redécouverte de la programmation orientée objet en Java

les termes separation, alignement, cohesion seront utilisés en référence au fichier comportement_base_boids.png dans le fichier imgs.

Objectifs visés ( ce ne sont que des idées, il n'est pas certains que tout soit implémenté, et pas forcément dans cet ordre ) :
- comportements basiques : évitement d'obstacles, séparation, alignement, cohesion sur un plan 2D
- adaptation des comportements précédents à un plan 3D -> prise en compte de vitesse prévue
- possibilité de sélectionner un individu ( qui changera de couleur ) afin de suivre son comportement
- possibilité de contrôler un individu sélectionné afin d'influencer le reste du groupe
- mise en place d'événements aléatoires ( vents, pluie ), mais suivant un pattern, qui auront des impacts sur nos Boids
- génération de terrain de façon procédurale
- événements aléatoires à comportement procédurale
- possibilité d'interragir avec le terrain ( se poser )
- gestion de faim, fatigue, et natalité/décès
- gestion de prédation
- propagation de maladie
- cycle jour/nuit
- relations intra-Boids ( pos, neutre, neg ) influençant leur comportement dans le pack