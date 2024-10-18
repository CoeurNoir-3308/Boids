package com.boids;

//ChatGPT


public class Vector2D {
    private double x;
    private double y;

    // Constructeur
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getter pour x et y
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Addition de vecteurs
    public Vector2D add(Vector2D v) {
        return new Vector2D(this.x + v.x, this.y + v.y);
    }

    // Soustraction de vecteurs
    public Vector2D sub(Vector2D v) {
        return new Vector2D(this.x - v.x, this.y - v.y);
    }

    // Multiplication par un scalaire
    public Vector2D mult(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    // Division par un scalaire
    public Vector2D div(double scalar) {
        return new Vector2D(this.x / scalar, this.y / scalar);
    }

    // Normaliser le vecteur (direction unitaire)
    public Vector2D normaliser() {
        double magnitude = Math.sqrt(x * x + y * y);
        if (magnitude > 0) {
            return new Vector2D(this.x / magnitude, this.y / magnitude);
        } else {
            return new Vector2D(0, 0); // Retourne un vecteur nul si la magnitude est 0
        }
    }

    // Calcul de la magnitude (longueur) du vecteur
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    // Retourner l'angle du vecteur en radians
    public double getAngle() {
        return Math.atan2(this.y, this.x);
    }

    // Créer un vecteur à partir d'un angle
    public static Vector2D fromAngle(double angle) {
        return new Vector2D(Math.cos(angle), Math.sin(angle));
    }

    @Override
    public String toString() {
        return "Vector2D(" + x + ", " + y + ")";
    }
}
