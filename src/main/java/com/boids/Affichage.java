package com.boids;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Affichage extends JPanel {
    private Monde monde;

    // Facteurs de rotation pour la projection isométrique
    private final double angleX = Math.toRadians(30); // Inclinaison pour l’axe X
    private final double angleY = Math.toRadians(45); // Inclinaison pour l’axe Y
    private final int offset = 250; // Décalage pour centrer l'affichage

    private Oiseau oiseauSelectionne = null;
    private boolean hautAppuye = false, basAppuye = false, gaucheAppuye = false, droiteAppuye = false;

    public Affichage(Monde monde) {
        this.monde = monde;
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clic = e.getPoint();
                verifierSelection(clic.x, clic.y);
                repaint();
            }
        });

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP : hautAppuye = true;
                    case KeyEvent.VK_DOWN : basAppuye = true;
                    case KeyEvent.VK_LEFT : gaucheAppuye = true;
                    case KeyEvent.VK_RIGHT : droiteAppuye = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP : hautAppuye = false;
                    case KeyEvent.VK_DOWN : basAppuye = false;
                    case KeyEvent.VK_LEFT : gaucheAppuye = false;
                    case KeyEvent.VK_RIGHT : droiteAppuye = false;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dessiner le cube en arrière-plan
        dessinerCube(g2d);

        // Dessiner les oiseaux
        ArrayList<Oiseau> oiseaux = monde.getOiseaux();
        for (Oiseau o : oiseaux) {
            dessinerDirection(g2d, o);
            dessinerOiseau(g2d, o);
        }

        if (oiseauSelectionne != null) {
            controlerOiseauSelectionne();
        }
    }

    private void verifierSelection(int x, int y) {
        ArrayList<Oiseau> oiseaux = monde.getOiseaux();
        Oiseau nouvelOiseauSelectionne = null;

        for (Oiseau o : oiseaux) {
            Point projection = projeterIsometrique(o.getX(), o.getY(), o.getZ());
            double distance = projection.distance(x, y);

            if (distance < 10) {
                nouvelOiseauSelectionne = o;
                break;
            }
        }

        if (nouvelOiseauSelectionne != null && nouvelOiseauSelectionne != oiseauSelectionne) {
            if (oiseauSelectionne != null) {
                oiseauSelectionne.setCouleur(Color.BLACK);
            }
            nouvelOiseauSelectionne.setCouleur(Color.GREEN);
            oiseauSelectionne = nouvelOiseauSelectionne;
        }
    }


    private void controlerOiseauSelectionne() {
        if (oiseauSelectionne == null) return;

        double vitesse = oiseauSelectionne.getVitesse();
        double deltaX = 0, deltaY = 0, deltaZ = 0;

        if (hautAppuye) deltaZ -= vitesse; // Monte
        if (basAppuye) deltaZ += vitesse; // Descend
        if (gaucheAppuye) {
            deltaX -= vitesse * Math.cos(oiseauSelectionne.getDirection()[0]);
            deltaY -= vitesse * Math.sin(oiseauSelectionne.getDirection()[0]);
        }
        if (droiteAppuye) {
            deltaX += vitesse * Math.cos(oiseauSelectionne.getDirection()[0]);
            deltaY += vitesse * Math.sin(oiseauSelectionne.getDirection()[0]);
        }

        // Appliquer les changements à l'oiseau sélectionné
        oiseauSelectionne.setX(oiseauSelectionne.getX() + deltaX);
        oiseauSelectionne.setY(oiseauSelectionne.getY() + deltaY);
        oiseauSelectionne.setZ(oiseauSelectionne.getZ() + deltaZ);
    }



    // Méthode pour projeter les coordonnées 3D en 2D pour la vue isométrique
    private Point projeterIsometrique(double x, double y, double z) {
        int xIso = (int) ((x - y) * Math.cos(angleY)) + offset;
        int yIso = (int) ((x + y) * Math.sin(angleX) - z) + offset;
        return new Point(xIso, yIso);
    }

    private void dessinerCube(Graphics2D g2d) {
        int largeur = monde.getLargeurFenetre();
        int hauteur = monde.getHauteurFenetre();
        int profondeur = monde.getProfondeurFenetre();

        // Sommet du cube
        Point[] sommets = new Point[8];
        sommets[0] = projeterIsometrique(0, 0, 0);
        sommets[1] = projeterIsometrique(largeur, 0, 0);
        sommets[2] = projeterIsometrique(largeur, hauteur, 0);
        sommets[3] = projeterIsometrique(0, hauteur, 0);
        sommets[4] = projeterIsometrique(0, 0, profondeur);
        sommets[5] = projeterIsometrique(largeur, 0, profondeur);
        sommets[6] = projeterIsometrique(largeur, hauteur, profondeur);
        sommets[7] = projeterIsometrique(0, hauteur, profondeur);

        // Arêtes du cube
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2));

        // Dessiner les arêtes du cube
        dessinerArete(g2d, sommets[0], sommets[1]); // Bas avant
        dessinerArete(g2d, sommets[1], sommets[2]);
        dessinerArete(g2d, sommets[2], sommets[3]);
        dessinerArete(g2d, sommets[3], sommets[0]);
        
        dessinerArete(g2d, sommets[4], sommets[5]); // Haut arrière
        dessinerArete(g2d, sommets[5], sommets[6]);
        dessinerArete(g2d, sommets[6], sommets[7]);
        dessinerArete(g2d, sommets[7], sommets[4]);
        
        dessinerArete(g2d, sommets[0], sommets[4]); // Arêtes verticales
        dessinerArete(g2d, sommets[1], sommets[5]);
        dessinerArete(g2d, sommets[2], sommets[6]);
        dessinerArete(g2d, sommets[3], sommets[7]);
    }

    private void dessinerArete(Graphics2D g2d, Point p1, Point p2) {
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    private void dessinerOiseau(Graphics2D g2d, Oiseau o) {
        Point p = projeterIsometrique(o.getX(), o.getY(), o.getZ());
        double directionX = o.getDirectionX();
        double directionY = o.getDirectionY();
        double directionZ = o.getDirectionZ();

        // Triangle représentant l'oiseau
        int longueur = 10;
        int largeur = 5;

        // Coordonnées du triangle en 2D après projection
        Point p1 = projeterIsometrique(
            o.getX() + longueur * directionX, 
            o.getY() + longueur * directionY, 
            o.getZ() + longueur * directionZ
        );
        
        Point p2 = projeterIsometrique(
            o.getX() - largeur * directionY, 
            o.getY() + largeur * directionX, 
            o.getZ()
        );

        Point p3 = projeterIsometrique(
            o.getX() + largeur * directionY, 
            o.getY() - largeur * directionX, 
            o.getZ()
        );

        g2d.setColor(o.getCouleur());
        g2d.fillPolygon(new int[]{p1.x, p2.x, p3.x}, new int[]{p1.y, p2.y, p3.y}, 3);
    }

    private void dessinerDirection(Graphics2D g2d, Oiseau o) {
        g2d.setColor(Color.RED);
        Point p = projeterIsometrique(o.getX(), o.getY(), o.getZ());
        double longueur = o.getVue() / 2;

        Point p2 = projeterIsometrique(
            o.getX() + longueur * o.getDirectionX(),
            o.getY() + longueur * o.getDirectionY(),
            o.getZ() + longueur * o.getDirectionZ()
        );

        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(p.x, p.y, p2.x, p2.y);
    }
}