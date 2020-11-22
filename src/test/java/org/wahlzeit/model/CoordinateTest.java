package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void getDistanceMustBe0() {
        //Arrange
        Coordinate coordinate1 = new Coordinate(1, 1, 1);
        Coordinate coordinate2 = new Coordinate(1, 1, 1);
        double expectedValue = 0;
        //Act
        double actualValue = coordinate1.getDistance(coordinate2);
        //Assert
        Assert.assertEquals(expectedValue, actualValue, 0);
    }

    @Test
    public void getDistanceMustBe6Point7082() {
        //Arrange
        Coordinate coordinate1 = new Coordinate(4, 6, 1);
        Coordinate coordinate2 = new Coordinate(2, 1, -3);
        double expectedValue = 6.7082;
        //Act
        double actualValue = coordinate1.getDistance(coordinate2);
        //Assert
        Assert.assertEquals(expectedValue, actualValue, 0.0001);
    }

    @Test(expected = NullPointerException.class)
    public void getDistanceCoordinateNull() {
        //Arrange
        Coordinate coordinate1 = new Coordinate(4, 6, 1);
        Coordinate coordinate = null;
        //Act
        coordinate1.getDistance(coordinate);
    }

    @Test
    public void isEqualTrue() {
        //Arrange
        Coordinate coordinate1 = new Coordinate(4, 6, 1);
        Coordinate coordinate2 = new Coordinate(4, 6, 1);
        boolean expectedValue = true;
        //Act
        boolean actualValue = coordinate1.isEqual(coordinate2);
        //Assert
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void isEqualSelf() {
        //Arrange
        Coordinate coordinate1 = new Coordinate(4, 6, 1);
        boolean expectedValue = true;
        //Act
        boolean actualValue = coordinate1.isEqual(coordinate1);
        //Assert
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void isEqualFalse() {
        //Arrange
        Coordinate coordinate1 = new Coordinate(4, 6, 1);
        Coordinate coordinate2 = new Coordinate(2, 1, -3);
        //Act
        boolean actualValue = coordinate1.isEqual(coordinate2);
        //Assert
        Assert.assertEquals(false, actualValue);
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testEqualsInvokesIsEqual() {
        //Arrange
        Coordinate coordinate = new Coordinate(4, 6, 1);
        Coordinate coordinate2 = new Coordinate(4, 6, 1);
        //Act
        boolean actualValue = coordinate.equals(coordinate2);
        boolean expectedValue = coordinate.isEqual(coordinate2);
        //Assert
        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testEqualsInvokesIsEqualFalse() {
        //Arrange
        Coordinate coordinate = new Coordinate(4, 6, 2);
        Coordinate coordinate2 = new Coordinate(4, 6, 1);
        //Act
        boolean actualValue = coordinate.equals(coordinate2);
        boolean expectedValue = coordinate.isEqual(coordinate2);
        //Assert
        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testEqualsWithNoCoordinate() {
        //Arrange
        Coordinate coordinate = new Coordinate(4, 6, 1);
        Object o = new Object();
        //Act
        boolean actualValue = coordinate.equals(o);
        //Assert
        Assert.assertEquals(false, actualValue);
    }
}
