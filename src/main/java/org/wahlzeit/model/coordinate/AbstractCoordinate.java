package org.wahlzeit.model.coordinate;

import org.wahlzeit.annotations.PatternInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@PatternInstance(
        patternName = "Flyweight",
        participants = {
                "AbstractCoordinate", "SphericCoordinate", "CartesianCoordinate"
        }
)
public abstract class AbstractCoordinate implements Coordinate {

    protected abstract CartesianCoordinate doAsCartesianCoordinate();

    protected abstract SphericCoordinate doAsSphericCoordinate();

    public final static Map<Integer,CartesianCoordinate> existingCartesianCoordinates = new HashMap<>();
    public final static Map<Integer,SphericCoordinate> existingSphericCoordinates = new HashMap<>();

    @Override
    public SphericCoordinate asSphericCoordinate() {
         assertClassInvariants();
         SphericCoordinate sphericCoordinate = doAsSphericCoordinate();
         assertClassInvariants();
         return sphericCoordinate;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();
        CartesianCoordinate cartesianCoordinate =  doAsCartesianCoordinate();
        assertClassInvariants();
        return cartesianCoordinate;
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) throws NullPointerException, ArithmeticException {
        CoordinateAsserter.assertNotNull(coordinate);
        double result = this.asCartesianCoordinate().getCartesianDistance(coordinate.asCartesianCoordinate());
        CoordinateAsserter.assertValidNumber(result);
        return result;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) throws NullPointerException, ArithmeticException{
        CoordinateAsserter.assertNotNull(coordinate);
        double result = this.asSphericCoordinate().getCentralAngle(coordinate.asSphericCoordinate());
        CoordinateAsserter.assertValidNumber(result);
        return result;
    }


    @Override
    public boolean isEqual(Coordinate coordinate) throws NullPointerException{
        CoordinateAsserter.assertNotNull(coordinate);
        double threshold = 0.000001;
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
        CartesianCoordinate thisAsCartesianCoordinate = this.asCartesianCoordinate();

        if (this == cartesianCoordinate) {
            return true;
        }

        boolean eqX = Math.abs(cartesianCoordinate.getX() - thisAsCartesianCoordinate.getX()) < threshold ? true : false;
        boolean eqY = Math.abs(cartesianCoordinate.getY() - thisAsCartesianCoordinate.getY()) < threshold ? true : false;
        boolean eqZ = Math.abs(cartesianCoordinate.getZ() - thisAsCartesianCoordinate.getZ()) < threshold ? true : false;

        return eqX && eqY && eqZ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate))
            return false;
        return this.isEqual((Coordinate) o);
    }

    @Override
    protected Object clone() {
        return this;
    }


    protected abstract void assertClassInvariants();

}
