package com.boids;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        
        int largeurFenetre = 800;
        int hauteurFenetre = 600;

        Monde monde = new Monde(largeurFenetre, hauteurFenetre);
        Oiseau o1 = new Oiseau(0, 0, Math.PI / 4);
        Oiseau o2 = new Oiseau(0, 0, Math.PI / 6);
        monde.ajouterOiseau(o1);
        monde.ajouterOiseau(o2);

        JFrame frame = new JFrame("animation Boids");
        Affichage affichage = new Affichage(monde);
        frame.add(affichage);
        frame.setSize(largeurFenetre, hauteurFenetre);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //timer pour mettre à jour l'animation
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