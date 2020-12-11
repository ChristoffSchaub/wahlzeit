package org.wahlzeit.model.coordinate;


public class CartesianCoordinate extends AbstractCoordinate {

    private double x;
    private double y;
    private double z;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }



    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        if (this instanceof CartesianCoordinate) {
            CartesianCoordinate cartesianCoordinate = this.asCartesianCoordinate();
            double radius = Math.sqrt(Math.pow(cartesianCoordinate.getX(), 2) + Math.pow(cartesianCoordinate.getY(), 2) + Math.pow(cartesianCoordinate.getZ(), 2));
            if (radius == 0)
                throw new ArithmeticException("Division through null is not possible");
            double theta = Math.acos(cartesianCoordinate.getZ() / radius);
            double phi = getPhi(cartesianCoordinate.getX(), cartesianCoordinate.getY(), cartesianCoordinate.getZ());
            return new SphericCoordinate(phi, theta, radius);
        }
        throw new IllegalArgumentException("This must be an instance of Cartesian or SphericCoordinate. Because this Abstract class can only be called by its subclasses");
    }




    public double getCartesianDistance(CartesianCoordinate coordinate) throws NullPointerException {
        return Math.sqrt(Math.pow(coordinate.getX() - this.getX(), 2) + Math.pow(coordinate.getY() - this.getY(), 2) + Math.pow(coordinate.getZ() - this.getZ(), 2));
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
