package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.coordinate.CartesianCoordinate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoTest {
    ResultSet resultSet = new CostumMockResultSet();

    @Before
    public void init() throws SQLException {
        //resultSet.updateInt("id",1);
    }

    @Test
    public void readFrom() throws SQLException {
        //Arrrange
        Photo photo = new Photo();
        //Act
        photo.readFrom(resultSet);
        //Assert
        Assert.assertTrue(photo.location.getCartesianCoordinate().equals(new CartesianCoordinate(1.0, 2.0, 3.0)));


    }

    @Test
    public void writeOn() throws SQLException {
        //Arrrange
        Location location = new Location(new CartesianCoordinate(1.0, 2.0, 3.0));
        //Act
        resultSet.updateString("location", Location.serializeAsString(location));

    }

}
