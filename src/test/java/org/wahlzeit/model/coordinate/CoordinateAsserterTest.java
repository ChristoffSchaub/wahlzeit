package org.wahlzeit.model.coordinate;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateAsserterTest {


    @Test
    public void assertNotNullTrue() {
        CoordinateAsserter.assertNotNull(new CartesianCoordinate(1, 1, 1));
    }

    @Test(expected = NullPointerException.class)
    public void assertNotNullFails() {
        CoordinateAsserter.assertNotNull(null);
    }

    @Test
    public void assertValidNumber() {
        CoordinateAsserter.assertValidNumber(1);
    }

    @Test(expected = ArithmeticException.class)
    public void assertValidNumberFails() {
        CoordinateAsserter.assertValidNumber(Double.NaN);
    }

    @Test
    public void assertDivisionThroughNull() {
        CoordinateAsserter.assertDivisionThroughNull(1);
    }

    @Test(expected = ArithmeticException.class)
    public void assertDivisionThroughNullFails() {
        CoordinateAsserter.assertDivisionThroughNull(0);
    }
}
