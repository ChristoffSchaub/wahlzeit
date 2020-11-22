package org.wahlzeit.model;

public class Location {

    protected Coordinate coordinate;

    public Location(Coordinate coordinate) {
        //TODO check if coordinate is allowed to be null
        if (coordinate == null)
            this.coordinate = null;
        this.coordinate = coordinate;
    }

    public Location(String locationAsString) {
        this.coordinate = deserializeCoordinateFromLocationString(locationAsString);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        if (coordinate == null)
            throw new IllegalArgumentException("coordinate must not be null");
        this.coordinate = coordinate;
    }

    public static String serializeAsString(Location l) {
        //if l is null the serializedString is also empty
        if (l == null)
            return "";
        String x = String.valueOf(l.coordinate.getX());
        String y = String.valueOf(l.coordinate.getX());
        String z = String.valueOf(l.coordinate.getX());
        return x + "," + y + "," + z;
    }

    private Coordinate deserializeCoordinateFromLocationString(String locationAsString) {
        //String is serialized with the scheme of the Method serializeAsString()
        if (locationAsString == null)
            throw new IllegalArgumentException("locationAsString must not be null");
        if (locationAsString.equals(""))
            return null;
        String[] coordinates = locationAsString.split(",");

        Double x = Double.parseDouble(coordinates[0]);
        Double y = Double.parseDouble(coordinates[1]);
        Double z = Double.parseDouble(coordinates[2]);

        return new Coordinate(x, y, z);
    }
}
