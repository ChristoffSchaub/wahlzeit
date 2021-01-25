package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;
import org.wahlzeit.services.SysLog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoodManager extends ObjectManager{
    private static FoodManager instance = null;
    private HashMap<String, Food> foods = new HashMap<String, Food>();
    private HashMap<String, FoodType> foodTypes = new HashMap<String, FoodType>();

    public static FoodManager getInstance() {
        if (instance == null) {
            instance = new FoodManager();
        }
        return instance;
    }

    public HashMap<String, Food> getFoods() {
        return foods;
    }

    public HashMap<String, FoodType> getFoodTypes() {
        return foodTypes;
    }

    public Food createFood(String foodTypeName, int calories) {
        assertIsValidFoodTypeName(foodTypeName);
        FoodType ft = getFoodType(foodTypeName);
        Food result = ft.createInstance(calories);
        foods.put(result.getId(), result);
        return result;
    }


    private void assertIsValidFoodTypeName(String foodTypeName) {
        assert (foodTypeName != null) : "tried to set null foodType";
        assert (this.foodTypes.get(foodTypeName) != null);
    }

    public FoodType getFoodType(String foodTypeName) {
        return this.foodTypes.get(foodTypeName);
    }
    public Food getFood(String id) {
        return this.foods.get(id);
    }


    @Override
    protected Persistent createObject(ResultSet rset) throws SQLException {
        int calories=rset.getInt("calories");
        FoodType type=rset.getObject("type",FoodType.class);
        return createFood(type.getFoodTypeName(),calories);
    }
}
