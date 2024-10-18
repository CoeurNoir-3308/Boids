package com.boids;

public class Oiseau {
    private double x, y;
    private double direction;
    private double vitesse;
    private double vue;
    
    public Oiseau(double x, double y, double direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.vitesse = 1.5;
        this.vue = 60;
    }

    public double distance(Oiseau autre) {
        double dx = this.x - autre.getX();
        double dy = this.y - autre.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getDirection() {
        return direction;
    }

    public double getVue(){
        return vue;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setDirection(double newDirection) {
        this.direction = newDirection;
    }

    public void deplacement() {
        x += vitesse * Math.cos(direction);
        y += vitesse * Math.sin(direction);
    }

    public void ajusterDirection(double delta) {
        this.direction += delta;
    }    
}