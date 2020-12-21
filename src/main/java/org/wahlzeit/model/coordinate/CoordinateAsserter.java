package org.wahlzeit.model.coordinate;

public class CoordinateAsserter {

    public static void assertNotNull(Coordinate coordinate) throws NullPointerException {
        if (coordinate == null)
            throw new NullPointerException("Coordinate must not be null");
    }

    public static void assertValidNumber(double number) throws ArithmeticException {
        if (Double.isNaN(number))
            throw new ArithmeticException("A number must not be NaN");
    }

    public static void assertDivisionThroughNull(double number) throws ArithmeticException {
        if (number == 0)
            throw new ArithmeticException("Division through null is not possible");
    }
}
