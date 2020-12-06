package org.wahlzeit.model.coordinate;

import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate {

    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhi(), getPhi(), getTheta());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Coordinate))
            return false;
        return super.isEqual((Coordinate) o);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
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
