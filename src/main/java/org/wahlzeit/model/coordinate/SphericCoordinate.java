package org.wahlzeit.model.coordinate;

public class SphericCoordinate extends AbstractCoordinate {

    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
        this.assertClassInvariants();
    }


    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();
        return doAsSphericCoordinate();
    }

    /**
     * Reference https://de.wikipedia.org/wiki/Kugelkoordinaten
     *
     * @return
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        CartesianCoordinate cartesianCoordinate = doAsCartesianCoordinate();
        CoordinateAsserter.assertValidNumber(cartesianCoordinate.getX());
        CoordinateAsserter.assertValidNumber(cartesianCoordinate.getY());
        CoordinateAsserter.assertValidNumber(cartesianCoordinate.getZ());
        return cartesianCoordinate;
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
        CoordinateAsserter.assertValidNumber(deltaTheta);
        CoordinateAsserter.assertValidNumber(preResult);
        double result = this.getRadius() * Math.acos(preResult);

        return result;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
        this.assertClassInvariants();
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
        this.assertClassInvariants();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        this.assertClassInvariants();
    }


    @Override
    protected CartesianCoordinate doAsCartesianCoordinate() {
        double x = this.getRadius() * Math.sin(this.getTheta()) * Math.cos(this.getPhi());
        double y = this.getRadius() * Math.sin(this.getTheta()) * Math.sin(this.getPhi());
        double z = this.getRadius() * Math.cos(this.getTheta());

        return new CartesianCoordinate(x, y, z);
    }

    @Override
    protected SphericCoordinate doAsSphericCoordinate() {
        return this;
    }

    @Override
    public void assertClassInvariants() {
        assert !Double.isNaN(this.getPhi()) && !Double.isNaN(this.getRadius()) && !Double.isNaN(this.getTheta());
    }
}
