package org.wahlzeit.model;

public class Location {

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    protected Coordinate coordinate;

    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


}
