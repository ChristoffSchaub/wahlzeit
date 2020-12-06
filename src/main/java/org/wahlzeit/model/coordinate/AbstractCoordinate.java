package org.wahlzeit.model.coordinate;

import java.util.IllegalFormatConversionException;

public abstract class AbstractCoordinate implements Coordinate {


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

    @Override
    public double getCartesianDistance(Coordinate coordinate) throws NullPointerException {
        if (coordinate == null)
            throw new NullPointerException("Coordinate must not be null");

        CartesianCoordinate cartesianCoordinate;
        cartesianCoordinate = coordinate.asCartesianCoordinate();
        CartesianCoordinate thisAsCartesianCoordinate = this.asCartesianCoordinate();
        return Math.sqrt(Math.pow(cartesianCoordinate.getX() - thisAsCartesianCoordinate.getX(), 2) + Math.pow(cartesianCoordinate.getY() - thisAsCartesianCoordinate.getY(), 2) + Math.pow(cartesianCoordinate.getZ() - thisAsCartesianCoordinate.getZ(), 2));
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

    /**
     * Reference https://en.wikipedia.org/wiki/Central_angle
     *
     * @param coordinate
     * @return
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate thisAsSphericCoordinate = this.asSphericCoordinate();
        if (coordinate == null)
            throw new NullPointerException("coordinate must not be null for calculating CentralAngle");

        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
        double deltaTheta = Math.abs(sphericCoordinate.getTheta() - thisAsSphericCoordinate.getTheta());
        double preResult = (Math.sin(thisAsSphericCoordinate.getPhi()) * Math.sin(sphericCoordinate.getPhi())) + (Math.cos(thisAsSphericCoordinate.getPhi()) * Math.cos(sphericCoordinate.getPhi()) * Math.cos(deltaTheta));
        double result = thisAsSphericCoordinate.getRadius() * Math.acos(preResult);

        return result;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null || !(coordinate instanceof Coordinate)) {
            return false;
        }
        double threshold = 0.000001;
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
        CartesianCoordinate thisAsCartesianCoordinate = this.asCartesianCoordinate();

        if (this == cartesianCoordinate) {
            return true;
        }

        boolean eqX = Math.abs(cartesianCoordinate.getX() - thisAsCartesianCoordinate.getX()) < threshold ? true : false;
        boolean eqY = Math.abs(cartesianCoordinate.getY() - thisAsCartesianCoordinate.getY()) < threshold ? true : false;
        boolean eqZ = Math.abs(cartesianCoordinate.getZ() - thisAsCartesianCoordinate.getZ()) < threshold ? true : false;

        return eqX && eqY && eqZ;
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
}
