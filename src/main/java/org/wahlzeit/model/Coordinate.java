package org.wahlzeit.model;

public interface Coordinate {

    CartesianCoordinate asCartesianCoordinate();

    double getCartesianDistance(Coordinate coordinate) throws NullPointerException;

    SphericCoordinate asSphericCoordinate();

    double getCentralAngle(Coordinate coordinate);

    boolean isEqual(Coordinate coordinate);

}
