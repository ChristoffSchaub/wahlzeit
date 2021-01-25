package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FoodPhoto extends Photo {

    /**
     * the average calories of a meal as init
     */
    protected int calories = 800;
    Logger logger = Logger.getLogger(FoodPhotoManager.class.getName());

    public PhotoManager foodPhotoManager= FoodPhotoManager.getInstance();

    protected Food food = new Food(new FoodType("Keine Angabe"),calories);

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
        this.foodPhotoManager.addPhoto(this);
    }

    /**
     * Inits a FoodPhoto with a specific Food
     *
     * @param food
     */
    public FoodPhoto(Food food) {
        super();
        this.food = food;
        this.foodPhotoManager.addPhoto(this);
    }

    /**
     *
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateInt("calories", this.food.getCalories());
    }

    /**
     *
     */
    @Override
    public void readFrom(ResultSet rset) throws SQLException,IllegalStateException {
        super.readFrom(rset);
        this.food.setCalories(rset.getInt("calories"));
        assertClassInvariants();
    }


    public int getCalories() {
        return this.food.getCalories();
    }

    public void setCalories(int calories) {
        this.food.setCalories(calories);
        assertClassInvariants();
    }


    public void assertClassInvariants() throws IllegalStateException{
        try {
            if (this.food.getCalories() < 0)
                throw new IllegalStateException("Calories must be greater than zero");
        }
        catch (NullPointerException exception){
            logger.log(Level.WARNING,"New FoodPhoto Class initiatet without relating food");
        }
    }
}
