package org.wahlzeit.model;

public class Location {

    protected Coordinate coordinate;

    public Location(Coordinate coordinate) {
        if(coordinate==null)
            throw new IllegalArgumentException("coordinate must not be null");
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        if(coordinate==null)
            throw new IllegalArgumentException("coordinate must not be null");
        this.coordinate = coordinate;
    }
}
