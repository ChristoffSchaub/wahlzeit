package org.wahlzeit.model;

import org.wahlzeit.model.coordinate.CartesianCoordinate;
import org.wahlzeit.model.coordinate.Coordinate;
import org.wahlzeit.model.coordinate.CoordinateAsserter;
import org.wahlzeit.model.coordinate.SphericCoordinate;

public class Location {

    protected Coordinate coordinate;

    public Location(CartesianCoordinate cartesianCoordinate) throws IllegalArgumentException, IllegalStateException{
        if (cartesianCoordinate == null) {
            throw new IllegalArgumentException("Coordinate must not be null");
        } else {
            this.coordinate = cartesianCoordinate;
            this.assertClassInvariants();
        }
    }

    public Location(SphericCoordinate sphericCoordinate) throws IllegalArgumentException, IllegalStateException{
        if (sphericCoordinate == null) {
            throw new IllegalArgumentException("Coordinate must not be null");
        } else {
            this.coordinate = sphericCoordinate;
            this.assertClassInvariants();
        }
    }

    public Location(String locationAsString) {
        this.coordinate = deserializeCoordinateFromLocationString(locationAsString);
        this.assertClassInvariants();
    }

    public static String serializeAsString(Location l) {
        //if l is null the serializedString is also empty
        if (l == null)
            return "";

        String x = String.valueOf(l.coordinate.asCartesianCoordinate().getX());
        String y = String.valueOf(l.coordinate.asCartesianCoordinate().getY());
        String z = String.valueOf(l.coordinate.asCartesianCoordinate().getZ());

        return x + "," + y + "," + z;
    }

    public static CartesianCoordinate deserializeCoordinateFromLocationString(String locationAsString) throws IllegalArgumentException {
        //String is serialized with the scheme of the Method serializeAsString()
        if (locationAsString == null)
            throw new IllegalArgumentException("locationAsString must not be null");
        if (locationAsString.equals(""))
            return null;

        Double x;
        Double z;
        Double y;
        String[] coordinates = locationAsString.split(",");

        if (coordinates.length != 3)
            throw new IllegalArgumentException("locationAsString must be in Format \"x,y,z\"");

        try {
            x = Double.parseDouble(coordinates[0]);
            y = Double.parseDouble(coordinates[1]);
            z = Double.parseDouble(coordinates[2]);
            return CartesianCoordinate.getCartesianCoordinate(x,y,z);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("locationAsString must be in Format x,y,z where x,y,z are doubles of coordinates");
        }
    }

    public static SphericCoordinate deserializeCoordinateFromLocationStringToSphericCoordinate(String locationAsString) throws IllegalArgumentException{
        return deserializeCoordinateFromLocationString(locationAsString).asSphericCoordinate();
    }

    public CartesianCoordinate getCartesianCoordinate() {
        return coordinate.asCartesianCoordinate();
    }

    public SphericCoordinate getSphericCoorinate() {
        return coordinate.asSphericCoordinate();
    }

    public void setCartesianCoordinate(CartesianCoordinate cartesianCoordinate) throws IllegalArgumentException{
        CoordinateAsserter.assertNotNull(cartesianCoordinate);
        this.coordinate = cartesianCoordinate;
    }

    public void setSphericCoordinate(SphericCoordinate sphericCoordinate) throws IllegalArgumentException{
        CoordinateAsserter.assertNotNull(sphericCoordinate);
        this.coordinate = sphericCoordinate;
    }

    public void assertClassInvariants() throws IllegalStateException{
        if( this.coordinate == null)
            throw new IllegalStateException("coordinate must not be Null");
    }
}
