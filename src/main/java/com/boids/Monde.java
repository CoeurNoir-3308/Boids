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
            verifierEvitementAutresOiseaux(o);
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

    private void verifierEvitementAutresOiseaux(Oiseau o) {
        for (Oiseau autre : oiseaux) {
            if (autre != o) {
                double distance = Math.sqrt(Math.pow(o.getX() - autre.getX(), 2) + Math.pow(o.getY() - autre.getY(), 2));
                if (distance < o.getVue() / 2) {
                    double angleEvitement = Math.atan2(o.getY() - autre.getY(), o.getX() - autre.getX());
                    //expression ternaire (remplace if else)
                    //fonctionnement : condition ? valeur_si_vrai : valeur_si_faux;
                    o.ajusterDirection(angleEvitement > o.getDirection() ? Math.PI / 60 : -Math.PI / 60);
                }
            }
        }
    }
    

    public ArrayList<Oiseau> getOiseaux() {
        return oiseaux;
    }
}