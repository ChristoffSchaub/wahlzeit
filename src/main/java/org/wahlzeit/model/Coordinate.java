package org.wahlzeit.model;

import java.util.Objects;

public class Coordinate {

    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public double getDistance(Coordinate coordinate) throws NullPointerException {
        if (coordinate == null)
            throw new NullPointerException("coordinate can not be null");
        return Math.sqrt(Math.pow(coordinate.getX() - this.getX(), 2) + Math.pow(coordinate.getY() - this.getY(), 2) + Math.pow(coordinate.getZ() - this.getZ(), 2));
    }

    public boolean isEqual(Coordinate coordinate) {
        double threshold=0.000001;

        if (coordinate == null) {
            return false;
        }

        if (this == coordinate) {
            return true;
        }

        boolean eqX = Math.abs(coordinate.getX() - this.getX())>threshold?true:false;
        boolean eqY = Math.abs(coordinate.getY() - this.getY())>threshold?true:false;
        boolean eqZ = Math.abs(coordinate.getZ() - this.getZ())>threshold?true:false;

        return eqX && eqY && eqZ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }

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

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinate)
            return this.isEqual((Coordinate) o);
        return false;
    }
}
