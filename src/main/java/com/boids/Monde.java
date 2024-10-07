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
            o.deplacement();
            verifierCollisionAvecBords(o);
        }
    }

    private void verifierCollisionAvecBords(Oiseau o) {
        if (o.getX() < 0) {
            o.setX(0);
            o.setDirection(Math.PI - o.getDirection());
        }
        if (o.getX() > largeurFenetre) {
            o.setX(largeurFenetre);
            o.setDirection(Math.PI - o.getDirection());
        }
        if (o.getY() < 0) {
            o.setY(0);
            o.setDirection(-o.getDirection());
        }
        if (o.getY() > hauteurFenetre) {
            o.setY(hauteurFenetre);
            o.setDirection(-o.getDirection());
        }
    }

    public ArrayList<Oiseau> getOiseaux() {
        return oiseaux;
    }
}