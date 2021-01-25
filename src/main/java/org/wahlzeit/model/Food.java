package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public class Food extends DataObject {

    protected FoodType type;
    protected String id;
    public FoodManager manager;


    protected int calories;

    public Food(FoodType foodType, int calories) {
        this.manager=FoodManager.getInstance();
        this.type = foodType;
        this.calories=calories;
        this.id= UUID.randomUUID().toString();
        this.manager.getFoods().put(this.id,this);
    }


    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public FoodType getType() {
        return type;
    }

    public FoodManager getManager() {
        return manager;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return getCalories() == food.getCalories() &&
                getType().equals(food.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getId(), getManager(), getCalories());
    }

    @Override
    public String getIdAsString() {
        return id;
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        assertNotNull(rset,"rset is null");
        this.id=rset.getString("id");
        this.calories=rset.getInt("calories");
        this.type=rset.getObject("type",this.type.getClass());

    }

    private void assertNotNull(Object param,String message) {
        assert (param!=null):message;
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        assertNotNull(rset,"rset is null");
        rset.updateString("id",this.getId());
        rset.updateInt("calories",this.getCalories());
        rset.updateObject("type",this.getCalories());
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        assertNotNull(stmt,"stmt is null");
        assertNotNull(pos, "pos is null");
        stmt.setString(pos, id);
    }
}
