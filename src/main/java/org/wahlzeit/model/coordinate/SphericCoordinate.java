package org.wahlzeit.model.coordinate;

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
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     * Reference https://de.wikipedia.org/wiki/Kugelkoordinaten
     *
     * @return
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        if (this instanceof SphericCoordinate) {
            SphericCoordinate sphericCoordinate = (SphericCoordinate) this;
            double x = sphericCoordinate.getRadius() * Math.sin(sphericCoordinate.getTheta()) * Math.cos(sphericCoordinate.getPhi());
            double y = sphericCoordinate.getRadius() * Math.sin(sphericCoordinate.getTheta()) * Math.sin(sphericCoordinate.getPhi());
            double z = sphericCoordinate.getRadius() * Math.cos(sphericCoordinate.getTheta());
            return new CartesianCoordinate(x, y, z);
        }
        throw new IllegalArgumentException("This must be an instance of Cartesian or SphericCoordinate. Because this Abstract class can only be called by its subclasses");
    }


    /**
     * Reference https://en.wikipedia.org/wiki/Central_angle
     *
     * @param coordinate
     * @return
     */
    public double getCentralAngle(SphericCoordinate coordinate) {

        double deltaTheta = Math.abs(coordinate.getTheta() - this.getTheta());
        double preResult = (Math.sin(this.getPhi()) * Math.sin(coordinate.getPhi())) + (Math.cos(this.getPhi()) * Math.cos(coordinate.getPhi()) * Math.cos(deltaTheta));
        double result = this.getRadius() * Math.acos(preResult);

        return result;
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
