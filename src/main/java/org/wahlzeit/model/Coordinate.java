package org.wahlzeit.model;

public class Coordinate {

    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }



    public double getDistance(Coordinate coordinate) throws NullPointerException{
        if (coordinate == null)
            throw  new NullPointerException("coordinate can not be null");
        return Math.sqrt(Math.pow(coordinate.getX() - this.getX(), 2) + Math.pow(coordinate.getY() - this.getY(), 2) + Math.pow(coordinate.getZ() - this.getZ(), 2));
    }

    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null)
            return false;
        if (this == coordinate) {
            return true;
        } else if (Double.compare(coordinate.getZ(), this.getZ())==0
                && Double.compare(coordinate.getY(), this.getY())==0
                && Double.compare(coordinate.getX(), this.getX())==0
        ) {
            return true;
        } else {
            return false;
        }
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
    public boolean equals(Object o){
        if(o instanceof Coordinate)
            return this.isEqual((Coordinate) o );
        return false;
    }
}
