package org.wahlzeit.model;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;

public class CartesianCoordinateTest {
    Double halfPi=Math.PI/2;

    @Test
    public void getDistanceMustBe0() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(1, 1, 1);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(1, 1, 1);
        double expectedValue = 0;
        //Act
        double actualValue = cartesianCoordinate1.getCartesianDistance(cartesianCoordinate2);
        //Assert
        Assert.assertEquals(expectedValue, actualValue, 0);
    }

    @Test
    public void getDistanceMustBe6Point7082() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(4, 6, 1);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(2, 1, -3);
        double expectedValue = 6.7082;
        //Act
        double actualValue = cartesianCoordinate1.getCartesianDistance(cartesianCoordinate2);
        //Assert
        Assert.assertEquals(expectedValue, actualValue, 0.0001);
    }

    @Test(expected = NullPointerException.class)
    public void getDistanceCoordinateNull() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(4, 6, 1);
        CartesianCoordinate cartesianCoordinate = null;
        //Act
        cartesianCoordinate1.getCartesianDistance(cartesianCoordinate);
    }

    @Test
    public void isEqualTrue() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(4, 6, 1);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(4, 6, 1);
        boolean expectedValue = true;
        //Act
        boolean actualValue = cartesianCoordinate1.isEqual(cartesianCoordinate2);
        //Assert
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void isEqualSelf() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(4, 6, 1);
        boolean expectedValue = true;
        //Act
        boolean actualValue = cartesianCoordinate1.isEqual(cartesianCoordinate1);
        //Assert
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void isEqualFalse() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(4, 6, 1);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(2, 1, -3);
        //Act
        boolean actualValue = cartesianCoordinate1.isEqual(cartesianCoordinate2);
        //Assert
        Assert.assertEquals(false, actualValue);
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testEqualsInvokesIsEqual() {
        //Arrange
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(4, 6, 1);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(4, 6, 1);
        //Act
        boolean actualValue = cartesianCoordinate.equals(cartesianCoordinate2);
        boolean expectedValue = cartesianCoordinate.isEqual(cartesianCoordinate2);
        //Assert
        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testEqualsInvokesIsEqualFalse() {
        //Arrange
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(4, 6, 2);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(4, 6, 1);
        //Act
        boolean actualValue = cartesianCoordinate.equals(cartesianCoordinate2);
        boolean expectedValue = cartesianCoordinate.isEqual(cartesianCoordinate2);
        //Assert
        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testEqualsWithNoCoordinate() {
        //Arrange
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(4, 6, 1);
        Object o = new Object();
        //Act
        boolean actualValue = cartesianCoordinate.equals(o);
        //Assert
        Assert.assertEquals(false, actualValue);
    }


    @Test
    public void asCartesianCoordinateWithCartesianCoordinate() {
        //Arrange
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(4, 6, 1);
        //Act
        cartesianCoordinate.asCartesianCoordinate();
        //Assert
        Assert.assertTrue(cartesianCoordinate instanceof CartesianCoordinate);
    }

    @Test
    public void asCartesianCoordinateWithSphericCoordinate() {
        //Arrange
        Coordinate sphericCoordinate = new SphericCoordinate(4, 6, 1);
        //Act
        sphericCoordinate = sphericCoordinate.asCartesianCoordinate();
        //Assert
        Assert.assertTrue(sphericCoordinate instanceof CartesianCoordinate);
    }

    @Test
    public void asCartesianCoordinateWithSphericCoordinateFails() {
        //Arrange
        Coordinate sphericCoordinate = new SphericCoordinate(4, 6, 1);
        //Assert
        Assert.assertFalse(sphericCoordinate instanceof CartesianCoordinate);
    }


    @Test
    public void asSphericCoordinate() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(2, 0, 0);
        SphericCoordinate sphericCoordinate = cartesianCoordinate.asSphericCoordinate();
        Assert.assertTrue(sphericCoordinate.getRadius() == 2);
        Assert.assertTrue(sphericCoordinate.getTheta() == halfPi);
        Assert.assertTrue(sphericCoordinate.getPhi() == 0);
    }


    @Test(expected = NullPointerException.class)
    public void asSphericCoordinateException() {
        CartesianCoordinate cartesianCoordinate = null;
        SphericCoordinate asSphericCoordinate = cartesianCoordinate.asSphericCoordinate();
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
        Coordinate cartesianCoordinate = new CartesianCoordinate(2, 0, 0);
        Coordinate sphericCoordinate = new SphericCoordinate(0,halfPi,2);
        Assert.assertTrue(cartesianCoordinate.equals(sphericCoordinate));
    }
    @Test(expected = NullPointerException.class)
    public void getCartesianDistanceNull() {
        Coordinate cartesianCoordinate = new CartesianCoordinate(2, 0, 0);
        cartesianCoordinate.getCartesianDistance(null);
    }
    @Test
    public void isEqualNull() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1,1,1);
        Assert.assertEquals(false,cartesianCoordinate.isEqual(null));

    }

}
