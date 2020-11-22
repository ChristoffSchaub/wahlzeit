package org.wahlzeit.model;

import java.text.ParseException;

public class Location {

    protected Coordinate coordinate;

    public Location(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate must not be null");
        } else {
            this.coordinate = coordinate;
        }
    }

    public Location(String locationAsString) {
        this.coordinate = deserializeCoordinateFromLocationString(locationAsString);
    }

    public static String serializeAsString(Location l) {
        //if l is null the serializedString is also empty
        if (l == null)
            return "";

        String x = String.valueOf(l.coordinate.getX());
        String y = String.valueOf(l.coordinate.getY());
        String z = String.valueOf(l.coordinate.getZ());

        return x + "," + y + "," + z;
    }

    public static Coordinate deserializeCoordinateFromLocationString(String locationAsString) {
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
            return new Coordinate(x, y, z);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("locationAsString must be in Format x,y,z where x,y,z are doubles of coordinates");
        }
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        if (coordinate == null)
            throw new IllegalArgumentException("coordinate must not be null");
        this.coordinate = coordinate;
    }
}
