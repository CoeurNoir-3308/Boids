package com.boids;

import java.util.ArrayList;

public class Monde {
    private ArrayList<Oiseau> oiseaux;
    private int largeurFenetre;
    private int hauteurFenetre;

    private double facteurSeparation = 1.0;
    private double facteurAlignement = 1.0;
    private double facteurCohesion = 1.0;

    public double getFacteurSeparation() {
        return facteurSeparation;
    }

    public void setFacteurSeparation(double facteurSeparation) {
        this.facteurSeparation = facteurSeparation;
    }

    public double getFacteurAlignement() {
        return facteurAlignement;
    }

    public void setFacteurAlignement(double facteurAlignement) {
        this.facteurAlignement = facteurAlignement;
    }

    public double getFacteurCohesion() {
        return facteurCohesion;
    }

    public void setFacteurCohesion(double facteurCohesion) {
        this.facteurCohesion = facteurCohesion;
    }

    public Monde(int largeurFenetre, int hauteurFenetre) {
        oiseaux = new ArrayList<>();
        this.largeurFenetre = largeurFenetre;
        this.hauteurFenetre = hauteurFenetre;
    }

    public void ajouterOiseau(Oiseau o) {
        oiseaux.add(o);
    }

    public void mettreAJour() {
        for (Oiseau o : oiseaux) {
            Vector2D separation = calculerSeparation(o).mult(getFacteurSeparation());
            Vector2D alignement = calculerAlignement(o).mult(getFacteurAlignement());
            Vector2D cohesion = calculerCohesion(o).mult(getFacteurCohesion());

            // Combine ces forces et ajuste la direction
            Vector2D forceTotale = separation.add(alignement).add(cohesion);
            o.ajusterDirection(forceTotale.getAngle());
            verifierEvitementMurs(o);
            o.deplacement();
        }
    }

    private Vector2D calculerSeparation(Oiseau o) {
        Vector2D forceSeparation = new Vector2D(0, 0);
        for (Oiseau autre : oiseaux) {
            if (autre != o) {
                double distance = o.distance(autre);
                if (distance < o.getVue() / 2) {
                    Vector2D difference = new Vector2D(o.getX() - autre.getX(), o.getY() - autre.getY());
                    forceSeparation = forceSeparation.add(difference.normaliser().div(distance));  // Évite de trop se rapprocher
                }
            }
        }
        return forceSeparation;
    }
    
    private Vector2D calculerAlignement(Oiseau o) {
        Vector2D directionMoyenne = new Vector2D(0, 0);
        int voisins = 0;
        for (Oiseau autre : oiseaux) {
            if (autre != o && o.distance(autre) < o.getVue()) {
                directionMoyenne = directionMoyenne.add(Vector2D.fromAngle(autre.getDirection()));
                voisins++;
            }
        }
        if (voisins > 0) {
            directionMoyenne = directionMoyenne.div(voisins);  // Moyenne des directions
            return directionMoyenne.normaliser();
        }
        return new Vector2D(0, 0);
    }
    
    private Vector2D calculerCohesion(Oiseau o) {
        Vector2D centreDeMasse = new Vector2D(0, 0);
        int voisins = 0;
        for (Oiseau autre : oiseaux) {
            if (autre != o && o.distance(autre) < o.getVue()) {
                centreDeMasse = centreDeMasse.add(new Vector2D(autre.getX(), autre.getY()));
                voisins++;
            }
        }
        if (voisins > 0) {
            centreDeMasse = centreDeMasse.div(voisins);  // Centre de masse des voisins
            return new Vector2D(centreDeMasse.getX() - o.getX(), centreDeMasse.getY() - o.getY()).normaliser();
        }
        return new Vector2D(0, 0);
    }
    

    private void verifierEvitementMurs(Oiseau o) {
        double direction = o.getDirection();
        
        // Rebonds avec le plafond (haut)
        if (o.getY() <= o.getVue() / 2) { // Plafond
            o.setY(o.getVue() / 2); // Empêche de sortir de l'écran
            o.setDirection(-direction); // Inversion de la direction verticale
        }
        // Rebonds avec le sol (bas)
        else if (o.getY() >= hauteurFenetre - (o.getVue() / 2)) { // Sol
            o.setY(hauteurFenetre - (o.getVue() / 2)); // Empêche de sortir de l'écran
            o.setDirection(-direction); // Inversion de la direction verticale
        }
        
        // Rebonds avec le mur droit
        if (o.getX() >= largeurFenetre - (o.getVue() / 2)) { // Mur droit
            o.setX(largeurFenetre - (o.getVue() / 2)); // Empêche de sortir de l'écran
            o.setDirection(Math.PI - direction); // Inversion de la direction horizontale
        }
        // Rebonds avec le mur gauche
        else if (o.getX() <= o.getVue() / 2) { // Mur gauche
            o.setX(o.getVue() / 2); // Empêche de sortir de l'écran
            o.setDirection(Math.PI - direction); // Inversion de la direction horizontale
        }
    }
    
    

    public boolean seVoient(Oiseau o1, Oiseau o2) {
        double distance = Math.sqrt(Math.pow(o1.getX() - o2.getX(), 2) + Math.pow(o1.getY() - o2.getY(), 2));
        return distance <= o1.getVue();
    }

    public ArrayList<Oiseau> getOiseaux() {
        return oiseaux;
    }
}