package org.wahlzeit.model.coordinate;

import org.wahlzeit.annotations.PatternInstance;

import java.util.Objects;

@PatternInstance(
        patternName = "Flyweight",
        participants = {
                "AbstractCoordinate", "SphericCoordinate", "CartesianCoordinate"
        }
)
public class SphericCoordinate extends AbstractCoordinate {

    private final double phi;
    private final double theta;
    private final double radius;

    private SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
        this.assertClassInvariants();
    }

    public static SphericCoordinate getSphericCoordinate(double phi, double theta, double radius){
        SphericCoordinate sphericCoordinate = new SphericCoordinate(phi,theta,radius);
        SphericCoordinate result = existingSphericCoordinates.get(sphericCoordinate.hashCode());
        if (result == null) {
            synchronized (existingSphericCoordinates) {
                result = existingSphericCoordinates.get(sphericCoordinate.hashCode());
                if (result == null) {
                    result = sphericCoordinate;
                    existingSphericCoordinates.put(sphericCoordinate.hashCode(), result);
                }
            }
        }
        return result;
    }

   /**
     * Reference https://en.wikipedia.org/wiki/Central_angle
     *
     * @param coordinate
     * @return
     */
    public double getCentralAngle(SphericCoordinate coordinate) throws ArithmeticException{
        double deltaTheta = Math.abs(coordinate.getTheta() - this.getTheta());
        double preResult = (Math.sin(this.getPhi()) * Math.sin(coordinate.getPhi())) + (Math.cos(this.getPhi()) * Math.cos(coordinate.getPhi()) * Math.cos(deltaTheta));
        CoordinateAsserter.assertValidNumber(deltaTheta);
        CoordinateAsserter.assertValidNumber(preResult);
        double result = this.getRadius() * Math.acos(preResult);
        return result;
    }

    public double getPhi() {
        return phi;
    }

    public SphericCoordinate setPhi(double phi) {
        SphericCoordinate coordinate = SphericCoordinate.getSphericCoordinate(phi,this.theta,this.radius);
        this.assertClassInvariants();
        return coordinate;
    }

    public double getTheta() {
        return theta;
    }

    public SphericCoordinate setTheta(double theta) {
        SphericCoordinate coordinate = SphericCoordinate.getSphericCoordinate(this.phi,theta,this.radius);
        assertClassInvariants();
        return coordinate;
    }

    public double getRadius() {
        return radius;
    }

    public SphericCoordinate setRadius(double radius) {
        SphericCoordinate coordinate=new SphericCoordinate(this.phi,this.theta,radius);
        this.assertClassInvariants();
        return coordinate;
    }


    @Override
    protected CartesianCoordinate doAsCartesianCoordinate() {
        double x = this.getRadius() * Math.sin(this.getTheta()) * Math.cos(this.getPhi());
        double y = this.getRadius() * Math.sin(this.getTheta()) * Math.sin(this.getPhi());
        double z = this.getRadius() * Math.cos(this.getTheta());

        return CartesianCoordinate.getCartesianCoordinate(x,y,z);
    }

    @Override
    protected SphericCoordinate doAsSphericCoordinate() {
        return this;
    }

    @Override
    public void assertClassInvariants() throws IllegalStateException{
        if(!(0<=this.theta&&this.theta<=Math.PI)){
            throw new IllegalStateException("Theta must be between 0 and Pi");
        }
        if(!(0<=this.getPhi()&&this.getPhi()<=2*Math.PI)||!((-1*Math.PI<=this.getPhi()&&this.getPhi()<=Math.PI))){
            throw new IllegalStateException("Phi must be between 0 and 2 Pi oder zwischen -Pi und Pi");
        }
        if(this.getRadius()<0)
            throw new IllegalStateException("Radius must not be negative");
        if( Double.isNaN(this.getPhi()) || Double.isNaN(this.getTheta()) || Double.isNaN(this.getRadius()))
            throw new IllegalStateException("X,Y,Z must not be NaN");
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.getPhi(), this.getTheta(), this.getRadius());
    }


}
