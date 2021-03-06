package org.wahlzeit.model.coordinate;


import org.wahlzeit.annotations.PatternInstance;

import java.util.Objects;

@PatternInstance(
        patternName = "Flyweight",
        participants = {
                "AbstractCoordinate", "SphericCoordinate", "CartesianCoordinate"
        }
)
public class CartesianCoordinate extends AbstractCoordinate {

    private final double x;
    private final double y;
    private final double z;

    private CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        assertClassInvariants();
    }

    public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z){
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x,y,z);
        CartesianCoordinate result = existingCartesianCoordinates.get(cartesianCoordinate.hashCode());
        if (result == null) {
            synchronized (existingCartesianCoordinates) {
                result = existingCartesianCoordinates.get(cartesianCoordinate.hashCode());
                if (result == null) {
                    result = cartesianCoordinate;
                    existingCartesianCoordinates.put(cartesianCoordinate.hashCode(), result);
                }
            }
        }
        return result;
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
        return SphericCoordinate.getSphericCoordinate(phi,theta,radius);
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

    public CartesianCoordinate setX(double x) {
        CartesianCoordinate coordinate = CartesianCoordinate.getCartesianCoordinate(x,this.getY(),this.getZ());
        assertClassInvariants();
        return coordinate;
    }

    public double getY() {
        return y;
    }

    public CartesianCoordinate setY(double y) {
        CartesianCoordinate coordinate = CartesianCoordinate.getCartesianCoordinate(this.getX(),y,this.getZ());
        assertClassInvariants();
        return coordinate;
    }

    public double getZ() {
        return z;
    }

    public CartesianCoordinate setZ(double z) {
        CartesianCoordinate coordinate = CartesianCoordinate.getCartesianCoordinate(this.getX(),this.getY(),z);
        assertClassInvariants();
        return coordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY(), this.getZ());
    }
}
