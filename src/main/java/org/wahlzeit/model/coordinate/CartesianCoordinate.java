package org.wahlzeit.model.coordinate;


public class CartesianCoordinate extends AbstractCoordinate {

    private double x;
    private double y;
    private double z;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        assertClassInvariants();
    }

    @Override
    protected CartesianCoordinate doAsCartesianCoordinate() {
        return this;
    }

    @Override
    protected SphericCoordinate doAsSphericCoordinate() throws ArithmeticException {
        double radius = Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));
        CoordinateAsserter.assertDivisionThroughNull(radius);
        double theta = Math.acos(this.getZ() / radius);
        double phi = getPhi(this.getX(), this.getY(), this.getZ());
        return new SphericCoordinate(phi, theta, radius);
    }



    public double getCartesianDistance(CartesianCoordinate coordinate) throws NullPointerException {
        CoordinateAsserter.assertNotNull(coordinate);
        return Math.sqrt(Math.pow(coordinate.getX() - this.getX(), 2) + Math.pow(coordinate.getY() - this.getY(), 2) + Math.pow(coordinate.getZ() - this.getZ(), 2));
    }

    private static double getPhi(double x, double y, double z) throws ArithmeticException{
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
        CoordinateAsserter.assertValidNumber(phi);
        return phi;
    }


    @Override
    public void assertClassInvariants() throws IllegalStateException{
        if( Double.isNaN(this.getX()) || Double.isNaN(this.getY()) || Double.isNaN(this.getZ()))
            throw new IllegalStateException("X,Y,Z must not be NaN");
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        assertClassInvariants();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        assertClassInvariants();
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
        assertClassInvariants();
    }
}
