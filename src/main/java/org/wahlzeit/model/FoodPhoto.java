package org.wahlzeit.model;

import org.wahlzeit.services.EmailAddress;
import org.wahlzeit.services.Language;
import org.wahlzeit.utils.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class FoodPhoto extends Photo {

    /**
     * the average calories of a meal as init
     */
    protected int calories = 800;

    public FoodPhoto(PhotoId id) {
        super(id);
    }

    public FoodPhoto(ResultSet rs) throws SQLException {
        super(rs);
    }

    public FoodPhoto() {
        super();
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
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        calories = rset.getInt("calories");
    }


    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
