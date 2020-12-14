package org.wahlzeit.model.coordinate;

import java.util.Objects;

public abstract class AbstractCoordinate implements Coordinate {

    @Override
    public double getCartesianDistance(Coordinate coordinate) throws NullPointerException {
        CoordinateAsserter.assertNotNull(coordinate);
        double result = this.asCartesianCoordinate().getCartesianDistance(coordinate.asCartesianCoordinate());
        CoordinateAsserter.assertValidNumber(result);
        return result;
    }

    protected abstract CartesianCoordinate doAsCartesianCoordinate();

    protected abstract SphericCoordinate doAsSphericCoordinate();

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        CoordinateAsserter.assertNotNull(coordinate);
        double result = this.asSphericCoordinate().getCentralAngle(coordinate.asSphericCoordinate());
        CoordinateAsserter.assertValidNumber(result);
        return result;
    }


    @Override
    public boolean isEqual(Coordinate coordinate) {
        CoordinateAsserter.assertNotNull(coordinate);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate))
            return false;
        return this.isEqual((Coordinate) o);
    }


    @Override
    public int hashCode() {
        CartesianCoordinate toHash = this.asCartesianCoordinate();
        return Objects.hash(toHash.getX(), toHash.getY(), toHash.getZ());
    }

    protected abstract void assertClassInvariants();

}
