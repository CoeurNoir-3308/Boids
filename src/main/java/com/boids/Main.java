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

        for(int i=0; i<250; i++){
            Oiseau o = new Oiseau();
            monde.ajouterOiseau(o);
        }
        
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