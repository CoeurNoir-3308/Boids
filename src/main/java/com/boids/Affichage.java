package com.boids;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Affichage extends JPanel {
    private Monde monde;

    public Affichage(Monde monde) {
        this.monde = monde;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        ArrayList<Oiseau> oiseaux = monde.getOiseaux();

        for (Oiseau o : oiseaux) {
            dessinerDirection(g2d, o);
            dessinerOiseau(g2d, o);
            dessinerVision(g2d, o);
        }
    }

    private void dessinerOiseau(Graphics2D g2d, Oiseau o) {
        double x = o.getX();
        double y = o.getY();
        double direction = o.getDirection();

        //params triangle
        int longueur = 10;
        int largeur = 5;

        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        xPoints[0] = (int) (x + longueur * Math.cos(direction));
        yPoints[0] = (int) (y + longueur * Math.sin(direction));

        xPoints[1] = (int) (x - largeur * Math.cos(direction + Math.PI / 2));
        yPoints[1] = (int) (y - largeur * Math.sin(direction + Math.PI / 2));

        xPoints[2] = (int) (x - largeur * Math.cos(direction - Math.PI / 2));
        yPoints[2] = (int) (y - largeur * Math.sin(direction - Math.PI / 2));

        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    private void dessinerVision(Graphics2D g2d, Oiseau o) {
        g2d.setColor(Color.BLUE);
        int rayon = (int) o.getVue();
        g2d.drawOval((int) o.getX() - rayon, (int) o.getY() - rayon, 2 * rayon, 2 * rayon);
    }

    private void dessinerDirection (Graphics2D g2d, Oiseau o){
        g2d.setColor(Color.RED);
        double x = o.getX();
        double y = o.getY();
        double direction = o.getDirection();
        double longueur = o.getVue() / 2;

        int x2 = (int) (x + longueur * Math.cos(direction));
        int y2 = (int) (y + longueur * Math.sin(direction));

        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine((int) x, (int) y, x2, y2);
    }
}