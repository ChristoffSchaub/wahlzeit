package org.wahlzeit.model.coordinate;

import org.junit.Assert;
import org.junit.Test;
import org.wahlzeit.model.coordinate.AbstractCoordinate;
import org.wahlzeit.model.coordinate.CartesianCoordinate;
import org.wahlzeit.model.coordinate.Coordinate;
import org.wahlzeit.model.coordinate.SphericCoordinate;

import static org.wahlzeit.model.coordinate.AbstractCoordinate.existingCartesianCoordinates;

public class CartesianCoordinateTest {
    Double halfPi = Math.PI / 2;


    @Test(expected = ArithmeticException.class)
    public void asCartesianCoordinateThrowsArithmeticExceptionRadiusIsNull() {
        //Arrange
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(0,0,0);
        //Act
        cartesianCoordinate.asSphericCoordinate();
    }


    @Test
    public void getDistanceMustBe0() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = CartesianCoordinate.getCartesianCoordinate(1,1,1);
        CartesianCoordinate cartesianCoordinate2 = CartesianCoordinate.getCartesianCoordinate(1,1,1);
        double expectedValue = 0;
        //Act
        double actualValue = cartesianCoordinate1.getCartesianDistance(cartesianCoordinate2);
        //Assert
        Assert.assertEquals(expectedValue, actualValue, 0);
    }

    @Test
    public void getDistanceMustBe6Point7082() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = CartesianCoordinate.getCartesianCoordinate(4,6,1);
        CartesianCoordinate cartesianCoordinate2 = CartesianCoordinate.getCartesianCoordinate(2,1,-3);
        double expectedValue = 6.7082;
        //Act
        double actualValue = cartesianCoordinate1.getCartesianDistance(cartesianCoordinate2);
        //Assert
        Assert.assertEquals(expectedValue, actualValue, 0.0001);
    }

    @Test(expected = NullPointerException.class)
    public void getDistanceCoordinateNull() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = CartesianCoordinate.getCartesianCoordinate(4,6,1);
        CartesianCoordinate cartesianCoordinate = null;
        //Act
        cartesianCoordinate1.getCartesianDistance(cartesianCoordinate);
    }

    @Test
    public void isEqualTrue() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = CartesianCoordinate.getCartesianCoordinate(4,6,1);
        CartesianCoordinate cartesianCoordinate2 = CartesianCoordinate.getCartesianCoordinate(4,6,1);
        boolean expectedValue = true;
        //Act
        boolean actualValue = cartesianCoordinate1.isEqual(cartesianCoordinate2);
        //Assert
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void isEqualSelf() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = CartesianCoordinate.getCartesianCoordinate(4,6,1);
        boolean expectedValue = true;
        //Act
        boolean actualValue = cartesianCoordinate1.isEqual(cartesianCoordinate1);
        //Assert
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void isEqualFalse() {
        //Arrange
        CartesianCoordinate cartesianCoordinate1 = CartesianCoordinate.getCartesianCoordinate(4,6,1);
        CartesianCoordinate cartesianCoordinate2 = CartesianCoordinate.getCartesianCoordinate(2,1,-3);
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
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(4,6,1);
        CartesianCoordinate cartesianCoordinate2 = CartesianCoordinate.getCartesianCoordinate(4,6,1);
        //Act
        boolean actualValue = cartesianCoordinate.equals(cartesianCoordinate2);
        boolean expectedValue = cartesianCoordinate.isEqual(cartesianCoordinate2);
        //Assert
        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testEqualsInvokesIsEqualFalse() {
        //Arrange
        CartesianCoordinate cartesianCoordinate =CartesianCoordinate.getCartesianCoordinate(4,6,2);
        CartesianCoordinate cartesianCoordinate2 = CartesianCoordinate.getCartesianCoordinate(4,6,1);
        //Act
        boolean actualValue = cartesianCoordinate.equals(cartesianCoordinate2);
        boolean expectedValue = cartesianCoordinate.isEqual(cartesianCoordinate2);
        //Assert
        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testEqualsWithNoCoordinate() {
        //Arrange
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(4,6,1);
        Object o = new Object();
        //Act
        boolean actualValue = cartesianCoordinate.equals(o);
        //Assert
        Assert.assertEquals(false, actualValue);
    }


    @Test
    public void asCartesianCoordinateWithCartesianCoordinate() {
        //Arrange
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(4,6,1);
        //Act
        cartesianCoordinate.asCartesianCoordinate();
        //Assert
        Assert.assertTrue(cartesianCoordinate instanceof CartesianCoordinate);
    }

    @Test
    public void asCartesianCoordinateWithSphericCoordinate() {
        //Arrange
        Coordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0.4,0.6,0.1);
        //Act
        sphericCoordinate = sphericCoordinate.asCartesianCoordinate();
        //Assert
        Assert.assertTrue(sphericCoordinate instanceof CartesianCoordinate);
    }

    @Test
    public void asCartesianCoordinateWithSphericCoordinateFails() {
        //Arrange
        Coordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0.2,0.6,1);
        //Assert
        Assert.assertFalse(sphericCoordinate instanceof CartesianCoordinate);
    }


    @Test
    public void asSphericCoordinate() {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2,0,0);
        SphericCoordinate sphericCoordinate = cartesianCoordinate.asSphericCoordinate();
        Assert.assertTrue(sphericCoordinate.getRadius() == 2);
        Assert.assertTrue(sphericCoordinate.getTheta() == halfPi);
        Assert.assertTrue(sphericCoordinate.getPhi() == 0);
    }

    @Test
    public void asSphericCoordinate2() {
        double threshold = 0.0001;
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(3,10,17);
        SphericCoordinate sphericCoordinate = cartesianCoordinate.asSphericCoordinate();
        Assert.assertTrue(Math.abs(sphericCoordinate.getRadius() - 19.94993734326) < threshold);
        Assert.assertTrue(Math.abs(sphericCoordinate.getTheta() - 0.55074859514) < threshold);
        Assert.assertTrue(Math.abs(sphericCoordinate.getPhi() - 1.279339532317) < threshold);
    }


    @Test(expected = NullPointerException.class)
    public void asSphericCoordinateException() {
        CartesianCoordinate cartesianCoordinate = null;
        SphericCoordinate asSphericCoordinate = cartesianCoordinate.asSphericCoordinate();
    }

    @Test
    public void getCentralAngle() {
        //Arrange
        SphericCoordinate sphericCoordinate1 = SphericCoordinate.getSphericCoordinate(0,halfPi,1);
        SphericCoordinate sphericCoordinate2 = SphericCoordinate.getSphericCoordinate(halfPi,halfPi,1);
        //Act
        double centralAngle = sphericCoordinate2.getCentralAngle(sphericCoordinate1);
        //Assert
        Assert.assertTrue(centralAngle == halfPi);
    }

    @Test
    public void isEqual() {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2,0,0);
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0,halfPi,2);
        Assert.assertTrue(cartesianCoordinate.equals(sphericCoordinate));
    }

    @Test
    public void equals() {
        Coordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2,0,0);
        Coordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0, halfPi, 2);
        Assert.assertTrue(cartesianCoordinate.equals(sphericCoordinate));
    }

    @Test(expected = NullPointerException.class)
    public void getCartesianDistanceNull() {
        Coordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2, 0, 0);
        cartesianCoordinate.getCartesianDistance(null);
    }

    @Test(expected = NullPointerException.class)
    public void isEqualNull() {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(1, 1, 1);
        cartesianCoordinate.isEqual(null);
    }

    @Test(expected = IllegalStateException.class)
    public void assertClassInvariantsFails() {
        double nan = Double.NaN;
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2, nan, 2);
    }

    @Test
    public void checkIfCartes() {
        Coordinate cartesianCoordinate =  CartesianCoordinate.getCartesianCoordinate(4,7,9);
        SphericCoordinate sphericCoordinate = cartesianCoordinate.asSphericCoordinate();
        CartesianCoordinate cartesianCoordinate1 = sphericCoordinate.asCartesianCoordinate();
        Assert.assertTrue(cartesianCoordinate.isEqual(cartesianCoordinate1));
        Assert.assertFalse(existingCartesianCoordinates.get(cartesianCoordinate.hashCode())==existingCartesianCoordinates.get(cartesianCoordinate1.hashCode()));
        Assert.assertTrue(existingCartesianCoordinates.get(cartesianCoordinate.hashCode()).equals(existingCartesianCoordinates.get(cartesianCoordinate1.hashCode())));
    }

}
