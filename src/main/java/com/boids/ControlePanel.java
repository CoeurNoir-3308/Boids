package com.boids;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ControlePanel extends JFrame {
    private Monde monde;

    public ControlePanel(Monde monde) {
        this.monde = monde;

        setTitle("Paramètres des comportements");
        setSize(400, 200);
        setLayout(new GridLayout(3, 2));

        // Slider pour la séparation
        add(new JLabel("Séparation"));
        JSlider separationSlider = new JSlider(0, 100, 50);
        separationSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                monde.setFacteurSeparation(separationSlider.getValue() / 50.0);
            }
        });
        add(separationSlider);

        // Slider pour l'alignement
        add(new JLabel("Alignement"));
        JSlider alignementSlider = new JSlider(0, 100, 50);
        alignementSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                monde.setFacteurAlignement(alignementSlider.getValue() / 50.0);
            }
        });
        add(alignementSlider);

        // Slider pour la cohésion
        add(new JLabel("Cohésion"));
        JSlider cohesionSlider = new JSlider(0, 100, 50);
        cohesionSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                monde.setFacteurCohesion(cohesionSlider.getValue() / 50.0);
            }
        });
        add(cohesionSlider);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
