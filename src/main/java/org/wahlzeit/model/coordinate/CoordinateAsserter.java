package org.wahlzeit.model.coordinate;

public  class CoordinateAsserter {

    protected static void assertNotNull(Coordinate coordinate) {
        if (coordinate == null)
            throw new NullPointerException("Coordinate must not be null");
    }

    protected static void assertValidNumber(double number) {
        if (Double.isNaN(number))
            throw new ArithmeticException("A number must not be NaN");
    }

    protected static void assertDivisionThroughNull(double number) {
        if (number == 0)
            throw new ArithmeticException("Division through null is not possible");
    }
}
