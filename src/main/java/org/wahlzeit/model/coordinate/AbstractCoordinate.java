package org.wahlzeit.model.coordinate;

import java.util.IllegalFormatConversionException;
import java.util.Objects;

public abstract class AbstractCoordinate implements Coordinate {

    @Override
    public double getCartesianDistance(Coordinate coordinate) throws NullPointerException {
        if (coordinate == null)
            throw new NullPointerException("Coordinate must not be null");
        return this.asCartesianCoordinate().getCartesianDistance(coordinate.asCartesianCoordinate());
    }
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        if (coordinate == null)
            throw new NullPointerException("coordinate must not be null for calculating CentralAngle");
        return this.asSphericCoordinate().getCentralAngle(coordinate.asSphericCoordinate());
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Coordinate))
            return false;
        return this.isEqual((Coordinate)o);
    }


    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
