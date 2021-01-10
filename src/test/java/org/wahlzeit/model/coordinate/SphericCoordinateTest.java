package org.wahlzeit.model.coordinate;

import org.junit.Assert;
import org.junit.Test;
import org.wahlzeit.model.coordinate.AbstractCoordinate;
import org.wahlzeit.model.coordinate.CartesianCoordinate;
import org.wahlzeit.model.coordinate.SphericCoordinate;

public class SphericCoordinateTest {
    Double halfPi = Math.PI / 2;


    @Test
    public void asCartesianCoordinate() {
        //Arrange
        double threshold = 0.0001;
        CartesianCoordinate cartesianCoordinate;
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0, halfPi, 2);
        //Act
        cartesianCoordinate = sphericCoordinate.asCartesianCoordinate();
        //Assert
        Assert.assertTrue(Math.abs(cartesianCoordinate.getX() - 2) < threshold);
        Assert.assertTrue(Math.abs(cartesianCoordinate.getY()) < threshold);
        Assert.assertTrue(Math.abs(cartesianCoordinate.getZ()) < threshold);
    }

    @Test
    public void getCartesianDistanceShouldBeNull() {
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0, halfPi, 2);
        SphericCoordinate sphericCoordinate2 = SphericCoordinate.getSphericCoordinate(0, halfPi, 2);
        double distance = sphericCoordinate.getCartesianDistance(sphericCoordinate2);
        Assert.assertEquals(0.0, distance, 0.000000001);
    }

    @Test
    public void asSphericCoordinate() {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2, 0, 0);
        SphericCoordinate sphericCoordinate = cartesianCoordinate.asSphericCoordinate();
        Assert.assertTrue(sphericCoordinate.getRadius() == 2);
        Assert.assertTrue(sphericCoordinate.getTheta() == halfPi);
        Assert.assertTrue(sphericCoordinate.getPhi() == 0);
    }

    @Test
    public void SphericAsSphericCoordinate() {
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(1, 1, 1);
        sphericCoordinate = sphericCoordinate.asSphericCoordinate();
        Assert.assertTrue(sphericCoordinate.getRadius() == 1);
        Assert.assertTrue(sphericCoordinate.getTheta() == 1);
        Assert.assertTrue(sphericCoordinate.getPhi() == 1);
    }

    @Test
    public void getCentralAngle() {
        //Arrange
        SphericCoordinate sphericCoordinate1 = SphericCoordinate.getSphericCoordinate(0, halfPi, 1);
        SphericCoordinate sphericCoordinate2 = SphericCoordinate.getSphericCoordinate(halfPi, halfPi, 1);
        //Act
        double centralAngle = sphericCoordinate2.getCentralAngle(sphericCoordinate1);
        //Assert
        Assert.assertTrue(centralAngle == halfPi);
    }

    @Test
    public void isEqual() {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2, 0, 0);
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0, halfPi, 2);
        Assert.assertTrue(cartesianCoordinate.equals(sphericCoordinate));
    }

    @Test
    public void equals() {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2, 0, 0);
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0, halfPi, 2);
        Assert.assertTrue(sphericCoordinate.equals(cartesianCoordinate));
    }

    @Test(expected = IllegalStateException.class)
    public void assertClassInvariantsFails() {
        double nan = Double.NaN;
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(nan, halfPi, 2);

    }




}
