package org.wahlzeit.model;

import java.util.Objects;

public class SphericCoordinate implements Coordinate {

    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    /**
     * Refernce https://de.wikipedia.org/wiki/Kugelkoordinaten
     * @return
     */

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = this.radius * Math.sin(this.theta) * Math.cos(phi);
        double y = this.radius * Math.sin(this.theta) * Math.sin(phi);
        double z = this.radius * Math.cos(this.theta);

        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) throws NullPointerException {
        return this.asCartesianCoordinate().getCartesianDistance(coordinate.asCartesianCoordinate());
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     * Reference https://en.wikipedia.org/wiki/Central_angle
     * @param coordinate
     * @return
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        if(coordinate==null)
            throw new NullPointerException("coordinate must not be null for calculating CentralAngle");
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
        double deltaTheta = Math.abs(sphericCoordinate.getTheta() - this.getTheta());
        double preResult = (Math.sin(this.getPhi()) * Math.sin(sphericCoordinate.getPhi())) + (Math.cos(this.getPhi()) * Math.cos(sphericCoordinate.getPhi()) * Math.cos(deltaTheta));
        double result = this.radius * Math.acos(preResult);

        return result;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        return this.asCartesianCoordinate().isEqual(coordinate.asCartesianCoordinate());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getPhi(), getPhi(), getTheta());
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
    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
