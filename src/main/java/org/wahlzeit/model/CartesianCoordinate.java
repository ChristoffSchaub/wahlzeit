package org.wahlzeit.model;

import java.util.Objects;

public class CartesianCoordinate implements Coordinate {

    private double x;
    private double y;
    private double z;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof CartesianCoordinate)
            return this.isEqual((CartesianCoordinate) o);
        if (o instanceof SphericCoordinate)
            return this.isEqual((SphericCoordinate) o);
        if (o instanceof Coordinate)
            return this.isEqual((Coordinate) o);

        return false;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) throws NullPointerException {
        if (coordinate == null)
            throw new NullPointerException("Coordinate must not be null");

        CartesianCoordinate cartesianCoordinate;
        cartesianCoordinate = coordinate.asCartesianCoordinate();

        return Math.sqrt(Math.pow(cartesianCoordinate.getX() - this.getX(), 2) + Math.pow(cartesianCoordinate.getY() - this.getY(), 2) + Math.pow(cartesianCoordinate.getZ() - this.getZ(), 2));
    }

    /***
     * Reference https://de.wikipedia.org/wiki/Kugelkoordinaten
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius = radius = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        double theta = theta = Math.acos(this.z / Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2)));
        double phi = getPhi(this.x, this.y, this.z);
        return new SphericCoordinate(phi, theta, radius);
    }

    private static double getPhi(double x, double y, double z) {
        double phi = 0;
        if (x > 0) {
            phi = Math.atan(y / x);
        } else if (x == 0) {
            phi = Math.signum(y) * Math.PI / 2;
        } else if ((x < 0) && (y >= 0)) {
            phi = Math.atan(y / x) + Math.PI;
        } else if ((x < 0) && (y < 0)) {
            phi = Math.atan(y / x) - Math.PI;
        }
        return phi;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        return this.asSphericCoordinate().getCentralAngle(coordinate.asSphericCoordinate());
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        }
        double threshold = 0.000001;
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();


        if (this == cartesianCoordinate) {
            return true;
        }

        boolean eqX = Math.abs(cartesianCoordinate.getX() - this.getX()) < threshold ? true : false;
        boolean eqY = Math.abs(cartesianCoordinate.getY() - this.getY()) < threshold ? true : false;
        boolean eqZ = Math.abs(cartesianCoordinate.getZ() - this.getZ()) < threshold ? true : false;

        return eqX && eqY && eqZ;
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
}
