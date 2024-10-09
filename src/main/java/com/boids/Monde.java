package com.boids;

import java.util.ArrayList;

public class Monde {
    private ArrayList<Oiseau> oiseaux;
    private int largeurFenetre;
    private int hauteurFenetre;

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
            verifierEvitementMurs(o);
            o.deplacement();
        }
    }

    private void verifierEvitementMurs(Oiseau o) {//Nos valeurs de PI sont en mirroir horizontal, avec PI et 2 * PI aux mêmes positions 

        if (o.getY() <= o.getVue() / 2) {//plafond
            if( (3 * Math.PI / 2) <= o.getDirection() && o.getDirection() <= (2 * Math.PI + 4 * Math.PI / 30) ){
                o.ajusterDirection(Math.PI / 30);
            }
            else{
                o.ajusterDirection( - Math.PI / 30);
            }
        }

        else if (o.getY() >= hauteurFenetre - ( o.getVue() /2 )) {//sol
            if( (Math.PI / 2) <= o.getDirection() && o.getDirection() <= (Math.PI  + 4 * Math.PI / 30) ){
                o.ajusterDirection(Math.PI / 30);
            }
            else{
                o.ajusterDirection( - Math.PI / 30);
            }
        }

        else if (o.getX() > largeurFenetre - ( o.getVue() / 2 )) {//mur droit
            if( 0 <= o.getDirection() && o.getDirection() < (Math.PI / 2) ){
                o.ajusterDirection( - Math.PI / 30); //pourquoi un moins ici, je ne sais pas, mais comprtement bizarre sans
            }
            else{
                o.ajusterDirection(Math.PI / 30);
            }
        }

        else if (o.getX() < o.getVue() / 2) {//mur gauche
            if( (Math.PI) <= o.getDirection() && o.getDirection() < (7 * Math.PI / 4) ){
                o.ajusterDirection(Math.PI / 30);
            }
            else {
                o.ajusterDirection( - Math.PI / 30);
            }
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