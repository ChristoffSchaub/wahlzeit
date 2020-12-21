package org.wahlzeit.model.coordinate;

public interface Coordinate {


    CartesianCoordinate asCartesianCoordinate();

    double getCartesianDistance(Coordinate coordinate) throws NullPointerException, ArithmeticException;

    SphericCoordinate asSphericCoordinate();

    double getCentralAngle(Coordinate coordinate) throws NullPointerException, ArithmeticException;

    boolean isEqual(Coordinate coordinate);

}
