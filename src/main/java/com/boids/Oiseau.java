package com.boids;
import java.awt.*;

public class Oiseau {
    private double x, y, z;
    private double directionX, directionY, directionZ;
    private double vitesse;
    private double vue;
    private Color couleur; 
    
    public Oiseau(double x, double y, double z, double directionX, double directionY, double directionZ) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.directionX = directionX;
        this.directionY = directionY;
        this.directionZ = directionZ;
        this.couleur = Color.BLACK;
        this.vitesse = 1;
        this.vue = 50;
    }

    public double distance(Oiseau autre) {
        double dx = this.x - autre.getX();
        double dy = this.y - autre.getY();
        double dz = this.z - autre.getZ();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    
    public double getDirectionX() {
        return directionX;
    }

    public double getDirectionY() {
        return directionY;
    }

    public double getDirectionZ() {
        return directionZ;
    }

    public double[] getDirection() {
        double azimut = Math.atan2(directionY, directionX);
        double hypotenuseXY = Math.sqrt(directionX * directionX + directionY * directionY);
        double elevation = Math.atan2(hypotenuseXY, directionZ);
        return new double[]{azimut, elevation};
    }

    public Vector3D getDirectionVector() {
        return new Vector3D(directionX, directionY, directionZ).normaliser();
    }
    
    public void setDirection(double azimut, double elevation) {
        directionX = Math.cos(azimut) * Math.sin(elevation);
        directionY = Math.sin(azimut) * Math.sin(elevation);
        directionZ = Math.cos(elevation);
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

    public void setZ(double z) {
        this.z = z;
    }

    public void setDirectionX(double newDirectionX) {
        this.directionX = newDirectionX;
    }

    public void setDirectionY(double newDirectionY) {
        this.directionY = newDirectionY;
    }

    public void setDirectionZ(double newDirectionZ) {
        this.directionZ = newDirectionZ;
    }

    public Color getCouleur(){
        return this.couleur;
    }

    public void setCouleur(Color couleur){
        this.couleur = couleur;
    }

    public void deplacement() {
        x += vitesse * directionX;
        y += vitesse * directionY;
        z += vitesse * directionZ;
    }

    public void ajusterDirection(double deltaX, double deltaY, double deltaZ) {
        this.directionX += deltaX;
        this.directionY += deltaY;
        this.directionZ += deltaZ;
    }  
    
    public double getVitesse(){
        return vitesse;
    }
}