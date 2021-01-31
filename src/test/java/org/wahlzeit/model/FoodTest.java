package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodTest {

    @Test
    public void createFood() {
        //Arrange
        FoodType foodType = new FoodType("Fette Burger");       //Act
        Food food = new Food(foodType,10000);
        //Assert
        Assert.assertEquals(foodType,food.getType());
        Assert.assertEquals(10000,food.getCalories());
        Assert.assertTrue(food.getManager()!=null);
    }

    @Test
    public void testEqualsTrue() {
        FoodType foodType = new FoodType("test");
        Food food = new Food(foodType,100);
        Food food2 = new Food(foodType,100);
        Assert.assertTrue(food.equals(food2));
    }
    @Test
    public void foodManagerTest() {
        FoodType foodType = new FoodType("BigMac");
        FoodManager foodManager = FoodManager.getInstance();
        foodManager.createFood("BigMac",1800);
    }

    @Test
    public void readFrom() {
    }

    @Test
    public void writeOn() {
    }

    @Test
    public void writeId() {
    }
}
