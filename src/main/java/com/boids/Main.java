package com.boids;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        
        int largeurFenetre = 500;
        int hauteurFenetre = 500;
        int profondeurFenetre = 500;

        Monde monde = new Monde(largeurFenetre, hauteurFenetre, profondeurFenetre);
        Oiseau o1 = new Oiseau(250, 250, 250, 1, 1, 1);
        Oiseau o2 = new Oiseau(240, 240, 240, -1, 1, 1);
        Oiseau o3 = new Oiseau(230, 230, 230, 1, 1, -1);
        Oiseau o4 = new Oiseau(220, 220, 220, 1, 1, 1);
        Oiseau o5 = new Oiseau(210, 210, 210, -1, 1, 1);
        Oiseau o6 = new Oiseau(200, 200, 200, 1, 1, -1);
        Oiseau o7 = new Oiseau(190, 190, 190, 1, 1, 1);
        Oiseau o8 = new Oiseau(180, 180, 180, -1, 1, 1);
        Oiseau o9 = new Oiseau(170, 170, 170, 1, 1, -1);
        Oiseau o10 = new Oiseau(160, 160, 160, 1, 1, 1);
        Oiseau o11 = new Oiseau(150, 150, 150, -1, 1, 1);
        Oiseau o12 = new Oiseau(140, 140, 140, 1, 1, -1);
        Oiseau o13 = new Oiseau(130, 130, 130, 1, 1, 1);
        Oiseau o14 = new Oiseau(120, 120, 120, -1, 1, 1);
        Oiseau o15 = new Oiseau(110, 110, 110, 1, 1, -1);
        
        monde.ajouterOiseau(o1);
        monde.ajouterOiseau(o2);
        monde.ajouterOiseau(o3);
        monde.ajouterOiseau(o4);
        monde.ajouterOiseau(o5);
        monde.ajouterOiseau(o6);
        monde.ajouterOiseau(o7);
        monde.ajouterOiseau(o8);
        monde.ajouterOiseau(o9);
        monde.ajouterOiseau(o10);
        monde.ajouterOiseau(o11);
        monde.ajouterOiseau(o12);
        monde.ajouterOiseau(o13);
        monde.ajouterOiseau(o14);
        monde.ajouterOiseau(o15);
        

        JFrame frame = new JFrame("Animation Boids 3D");
        Affichage affichage = new Affichage(monde);
        frame.add(affichage);
        frame.setSize(largeurFenetre + 200, hauteurFenetre + 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new ControlePanel(monde);
        
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monde.mettreAJour();
                affichage.repaint();
            }
        });
        timer.start();
    }
}