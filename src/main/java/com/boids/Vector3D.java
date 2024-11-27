package com.boids;

public class Vector3D {
    private double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    public Vector3D add(Vector3D v) {
        return new Vector3D(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vector3D subtract(Vector3D v) {
        return new Vector3D(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Vector3D mult(double scalar) {
        if(scalar == 0){
            return new Vector3D(this.x, this.y, this.z);
        }
        double xTemp = this.x * scalar;
        double yTemp = this.y * scalar;
        double zTemp = this.z * scalar;
        if(xTemp == 0){
            xTemp = 1;
        }
        if(yTemp == 0){
            yTemp = 1;
        }
        if(zTemp == 0){
            zTemp = 1;
        }
        return new Vector3D(xTemp, yTemp, zTemp);
    }

    public double dotProduct(Vector3D v) {
        double xTemp = this.x * v.x;
        double yTemp = this.y * v.y;
        double zTemp = this.z * v.z;
        if(xTemp + yTemp + zTemp == 0){
            if(this.x != 0){
                return this.x+this.y+this.z;
            }
            else{
                return v.x+v.y+v.z;
            }
        }
        return xTemp + yTemp + zTemp;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3D normaliser() {
        double magnitude = magnitude();
        if (magnitude == 0) return new Vector3D(0, 0, 0);
        return new Vector3D(x / magnitude, y / magnitude, z / magnitude);
    }

    @Override
    public String toString() {
        return "Vector3D(" + x + ", " + y + ", " + z + ")";
    }

    public Vector3D div(double scalar) {
        return new Vector3D(this.x / scalar, this.y / scalar, this.z/scalar);
    }

    public double[] getAngles() {
        double azimut = Math.atan2(y, x);
        
        double hypotenuseXY = Math.sqrt(x * x + y * y);
        double elevation = Math.atan2(hypotenuseXY, z);
    
        return new double[]{azimut, elevation};
    }
    
}
