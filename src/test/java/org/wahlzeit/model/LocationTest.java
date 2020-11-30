package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class LocationTest {

    @Test
    public void serializeAsString() {
        //Arrange
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(100.0, 200.0, 300.0);
        Location location = new Location(cartesianCoordinate);
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
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(100.0, 200.0, 300.0);
        //Act
        CartesianCoordinate actualCartesianCoordinate = Location.deserializeCoordinateFromLocationString(locationString);
        //Assert
        Assert.assertTrue(cartesianCoordinate.equals(actualCartesianCoordinate));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deserializeCoordinateFromNullLocationString() {
        //Arrange
        String locationString = null;
        //Act
        CartesianCoordinate actualCartesianCoordinate = Location.deserializeCoordinateFromLocationString(locationString);
    }

    @Test()
    public void deserializeCoordinateFromEmptyLocationString() {
        //Arrange
        String locationString = "";
        //Act
        CartesianCoordinate actualCartesianCoordinate = Location.deserializeCoordinateFromLocationString(locationString);
        //Assert
        Assert.assertTrue(actualCartesianCoordinate == null);
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

    @Test
    public void deserializeLocationWithSphericCoordinate() {
        //Arrange
        String locationString = "100.0,200.0,300.0";
        //Act
        Location.deserializeCoordinateFromLocationStringToSphericCoordinate(locationString);
    }
    @Test
    public void createLocationWithSphericCoordinate() {
        //Arrange
        Location sphericLocation = new Location(new SphericCoordinate(1,1,1));

    }


}
