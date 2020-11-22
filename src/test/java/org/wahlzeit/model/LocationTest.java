package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void serializeAsString() {
        //Arrange
        Coordinate coordinate = new Coordinate(100.0, 200.0, 300.0);
        Location location = new Location(coordinate);
        String expectedValue = "100.0,200.0,300.0";
        //Act
        String actualValue = Location.serializeAsString(location);
        //Assert
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void serializeNullString() {
        //Arrange
        Location location = null;
        String expectedValue = "";
        //Act
        String actualValue = Location.serializeAsString(location);
        //Assert
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void deserializeCoordinateFromLocationString() {
        //Arrange
        String locationString = "100.0,200.0,300.0";
        Coordinate coordinate = new Coordinate(100.0, 200.0, 300.0);
        //Act
        Coordinate actualCoordinate = Location.deserializeCoordinateFromLocationString(locationString);
        //Assert
        Assert.assertTrue(coordinate.equals(actualCoordinate));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deserializeCoordinateFromNullLocationString() {
        //Arrange
        String locationString = null;
        //Act
        Coordinate actualCoordinate = Location.deserializeCoordinateFromLocationString(locationString);
    }

    @Test()
    public void deserializeCoordinateFromEmptyLocationString() {
        //Arrange
        String locationString = "";
        //Act
        Coordinate actualCoordinate = Location.deserializeCoordinateFromLocationString(locationString);
        //Assert
        Assert.assertTrue(actualCoordinate == null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deserializeCoordinateFromWrongLengthLocationString() {
        //Arrange
        String locationString = "100.0,200.0,300.0,44444";
        //Act
        Location.deserializeCoordinateFromLocationString(locationString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deserializeCoordinateFromNotDoubleLocationString() {
        //Arrange
        String locationString = "100.0,200.0,X.0";
        //Act
        Location.deserializeCoordinateFromLocationString(locationString);
    }


}
