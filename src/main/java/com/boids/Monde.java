package com.boids;

import java.util.ArrayList;

public class Monde {
    private ArrayList<Oiseau> oiseaux;
    private int largeurFenetre;
    private int hauteurFenetre;
    private int profondeurFenetre;

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

    public Monde(int largeurFenetre, int hauteurFenetre, int profondeurFenetre) {
        oiseaux = new ArrayList<>();
        this.largeurFenetre = largeurFenetre;
        this.hauteurFenetre = hauteurFenetre;
        this.profondeurFenetre = profondeurFenetre;
    }

    public void ajouterOiseau(Oiseau o) {
        oiseaux.add(o);
    }

    public void mettreAJour() {
        for (Oiseau o : oiseaux) {
            Vector3D separation = calculerSeparation(o).mult(getFacteurSeparation());
            Vector3D alignement = calculerAlignement(o).mult(getFacteurAlignement());
            Vector3D cohesion = calculerCohesion(o).mult(getFacteurCohesion());

            Vector3D forceTotale = separation.add(alignement).add(cohesion);
        
            double[] angles = forceTotale.getAngles();
            o.setDirection(angles[0], angles[1]);

            verifierEvitementMurs(o);
            o.deplacement();
        }
    }

    private Vector3D calculerSeparation(Oiseau o) {
        Vector3D forceSeparation = new Vector3D(0, 0, 0);
        for (Oiseau autre : oiseaux) {
            if (autre != o) {
                double distance = o.distance(autre);
                if (distance < o.getVue() / 4) {
                    Vector3D difference = new Vector3D(o.getX() - autre.getX(), o.getY() - autre.getY(), o.getZ() - autre.getZ());
                    difference = difference.normaliser().div(distance);
                    forceSeparation = forceSeparation.add(difference);
                }
            }
        }
        return forceSeparation.normaliser();
    }    
    
    private Vector3D calculerAlignement(Oiseau o) {
        Vector3D directionMoyenne = new Vector3D(0, 0, 0);
        int voisins = 0;
        
        for (Oiseau autre : oiseaux) {
            if (autre != o && o.distance(autre) < o.getVue()) {
                directionMoyenne = directionMoyenne.add(autre.getDirectionVector());
                voisins++;
            }
        }
        
        if (voisins > 0) {
            directionMoyenne = directionMoyenne.div(voisins);
            return directionMoyenne.normaliser();
        }
        
        return new Vector3D(0, 0, 0);
    }
    
    
    
    private Vector3D calculerCohesion(Oiseau o) {
        Vector3D centreDeMasse = new Vector3D(0, 0, 0);
        int voisins = 0;
        for (Oiseau autre : oiseaux) {
            if (autre != o && o.distance(autre) < o.getVue()) {
                centreDeMasse = centreDeMasse.add(new Vector3D(autre.getX(), autre.getY(), autre.getZ()));
                voisins++;
            }
        }
        if (voisins > 0) {
            centreDeMasse = centreDeMasse.div(voisins);
            Vector3D directionVersCentre = new Vector3D(centreDeMasse.getX() - o.getX(), centreDeMasse.getY() - o.getY(), centreDeMasse.getZ() - o.getZ());
            return directionVersCentre.normaliser();
        }
        return new Vector3D(0, 0, 0);
    }
    
    

    private void verifierEvitementMurs(Oiseau o) {
        double vue = o.getVue() / 2;
    
        if (o.getY() <= vue) { // Plafond
            o.setY(vue);
            o.setDirection(o.getDirection()[0], -o.getDirection()[1]);
        }
        
        else if (o.getY() >= hauteurFenetre - vue) { // Sol
            o.setY(hauteurFenetre - vue);
            o.setDirection(o.getDirection()[0], -o.getDirection()[1]);
        }
    
        // Vérifie collision avec le mur droit
        if (o.getX() >= largeurFenetre - vue) { // Mur droit
            o.setX(largeurFenetre - vue);
            o.setDirection(Math.PI - o.getDirection()[0], o.getDirection()[1]);
        }
        
        else if (o.getX() <= vue) { // Mur gauche
            o.setX(vue);
            o.setDirection(Math.PI - o.getDirection()[0], o.getDirection()[1]);
        }
    
        if (o.getZ() >= profondeurFenetre - vue) { // Face arrière
            o.setZ(profondeurFenetre - vue);
            o.setDirection(o.getDirection()[0], Math.PI - o.getDirection()[1]);
        }
        
        else if (o.getZ() <= vue) { // Face avant
            o.setZ(vue);
            o.setDirection(o.getDirection()[0], Math.PI - o.getDirection()[1]);
        }
    }
    public boolean seVoient(Oiseau o1, Oiseau o2) {
        double distance = o1.distance(o2);
        return distance <= o1.getVue();
    }

    public ArrayList<Oiseau> getOiseaux() {
        return oiseaux;
    }

    public int getLargeurFenetre(){
        return largeurFenetre;
    }

    public int getHauteurFenetre(){
        return hauteurFenetre;
    }

    public int getProfondeurFenetre(){
        return profondeurFenetre;
    }
}