package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SphericCoordinateTest {
    Double halfPi=Math.PI/2;
    @Test
    public void asCartesianCoordinate() {
        //Arrange
        CartesianCoordinate cartesianCoordinate ;
        SphericCoordinate sphericCoordinate = new SphericCoordinate(0,halfPi,2);
        //Act
        cartesianCoordinate= sphericCoordinate.asCartesianCoordinate();
        //Assert
        Assert.assertTrue(cartesianCoordinate.getX() == 2);
        Assert.assertTrue(cartesianCoordinate.getY() == halfPi);
        Assert.assertTrue(cartesianCoordinate.getZ() == 0);
    }

    @Test
    public void getCartesianDistanceShouldBeNull() {
        SphericCoordinate sphericCoordinate = new SphericCoordinate(0,halfPi,2);
        SphericCoordinate sphericCoordinate2 = new SphericCoordinate(0,halfPi,2);
        double distance=sphericCoordinate.getCartesianDistance(sphericCoordinate2);
        Assert.assertEquals(0.0,distance,0.000000001);
    }

    @Test
    public void asSphericCoordinate() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(2, 0, 0);
        SphericCoordinate sphericCoordinate = cartesianCoordinate.asSphericCoordinate();
        Assert.assertTrue(sphericCoordinate.getRadius() == 2);
        Assert.assertTrue(sphericCoordinate.getTheta() == halfPi);
        Assert.assertTrue(sphericCoordinate.getPhi() == 0);
    }

    @Test
    public void getCentralAngle() {
        //Arrange
        SphericCoordinate sphericCoordinate1 = new SphericCoordinate(0, halfPi, 1);
        SphericCoordinate sphericCoordinate2 = new SphericCoordinate(halfPi, halfPi, 1);
        //Act
        double centralAngle = sphericCoordinate2.getCentralAngle(sphericCoordinate1);
        //Assert
        Assert.assertTrue(centralAngle == halfPi);
    }

    @Test
    public void isEqual() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(2, 0, 0);
        SphericCoordinate sphericCoordinate = new SphericCoordinate(0,halfPi,2);
        Assert.assertTrue(cartesianCoordinate.equals(sphericCoordinate));
    }

    @Test
    public void equals() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(2, 0, 0);
        SphericCoordinate sphericCoordinate = new SphericCoordinate(0,halfPi,2);
        Assert.assertTrue(sphericCoordinate.equals(cartesianCoordinate));
    }

}
