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







    /**
     * Reference https://en.wikipedia.org/wiki/Central_angle
     *
     * @param coordinate
     * @return
     */
    public double getCentralAngle(SphericCoordinate coordinate) throws ArithmeticException{
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
    public void assertClassInvariants() throws IllegalStateException{
        if(!(0<=this.theta&&this.theta<=Math.PI)){
            throw new IllegalStateException("Theta must be between 0 and Pi");
        }
        if(!(0<=this.getPhi()&&this.getPhi()<=2*Math.PI)||!((-1*Math.PI<=this.getPhi()&&this.getPhi()<=Math.PI))){
            throw new IllegalStateException("Phi must be between 0 and 2 Pi oder zwischen -Pi und Pi");
        }
        if(this.getRadius()<0)
            throw new IllegalStateException("Radius must not be negative");
        if( Double.isNaN(this.getPhi()) || Double.isNaN(this.getTheta()) || Double.isNaN(this.getRadius()))
            throw new IllegalStateException("X,Y,Z must not be NaN");
    }
}
