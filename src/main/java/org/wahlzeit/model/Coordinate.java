package org.wahlzeit.model;

public class Coordinate {

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private double x;
    private double y;
    private double z;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getDistance(Coordinate coordinate) {
        return Math.sqrt(Math.pow(coordinate.getX() - this.getX(), 2) + Math.pow(coordinate.getY() - this.getY(), 2) + Math.pow(coordinate.getZ() - this.getZ(), 2));
    }

    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null)
            return false;
        if (this == coordinate) {
            return true;
        } else if (coordinate.getZ() == this.getZ()
                && coordinate.getY() == this.getY()
                && coordinate.getX() == this.getX()
        ) {
            return true;
        } else {
            return false;
        }
    }

}
