package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodPhoto extends Photo {

    /**
     * the average calories of a meal as init
     */
    protected int calories = 800;

    public FoodPhoto(PhotoId id) throws IllegalStateException{
        super(id);
        assertClassInvariants();
    }

    public FoodPhoto(ResultSet rs) throws SQLException {
        super(rs);
        assertClassInvariants();
    }

    public FoodPhoto() throws IllegalStateException{
        super();
        assertClassInvariants();
    }

    /**
     * Inits a FoodPhoto with a specific calorie number
     *
     * @param calories
     */
    public FoodPhoto(int calories) {
        super();
        this.calories = calories;
    }

    /**
     *
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateInt("calories", calories);
    }

    /**
     *
     */
    @Override
    public void readFrom(ResultSet rset) throws SQLException,IllegalStateException {
        super.readFrom(rset);
        calories = rset.getInt("calories");
        assertClassInvariants();
    }


    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
        assertClassInvariants();
    }


    public void assertClassInvariants() throws IllegalStateException{
        if( this.calories<0)
            throw new IllegalStateException("Calories must be greater than zero");
    }
}
