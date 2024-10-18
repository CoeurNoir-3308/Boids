package com.boids;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        
        int largeurFenetre = 800;
        int hauteurFenetre = 600;

        Monde monde = new Monde(largeurFenetre, hauteurFenetre);
        Oiseau o1 = new Oiseau(70, 70, 7 * Math.PI / 4);
        Oiseau o2 = new Oiseau(100, 100, 2 * Math.PI);
        Oiseau o3 = new Oiseau(130, 130, Math.PI / 2);
        Oiseau o4 = new Oiseau(160, 160, 3 * Math.PI / 4);
        Oiseau o5 = new Oiseau(190, 190, 5 * Math.PI / 4);
        Oiseau o6 = new Oiseau(220, 220, Math.PI);
        monde.ajouterOiseau(o1);
        monde.ajouterOiseau(o2);
        monde.ajouterOiseau(o3);
        monde.ajouterOiseau(o4);
        monde.ajouterOiseau(o5);
        monde.ajouterOiseau(o6);

        JFrame frame = new JFrame("animation Boids");
        Affichage affichage = new Affichage(monde);
        frame.add(affichage);
        frame.setSize(largeurFenetre, hauteurFenetre);
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