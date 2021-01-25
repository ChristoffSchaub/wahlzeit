package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class FoodManagerTest {

    @Test
    public void getInstance() {
        FoodManager foodManager1=FoodManager.getInstance();
        FoodManager foodManager2=FoodManager.getInstance();
        Assert.assertEquals(foodManager1,foodManager2);
    }

    @Test
    public void createFood() {
        FoodManager foodManager = FoodManager.getInstance();
        FoodType foodType = new FoodType("test");
        Food food = foodManager.createFood("test",1000);
        Assert.assertTrue(foodManager.getFoods().containsValue(food));
    }

    @Test
    public void createFoodType() {
        FoodManager foodManager = FoodManager.getInstance();
        FoodType foodType = new FoodType("test");
        Assert.assertTrue(foodManager.getFoodTypes().containsValue(foodType));
    }

    @Test
    public void hierarchyTest() {
        //                  top
        //            --------------------
        //      middle      middle2     middle3
        //   ------------
        // subleft subleft2

        FoodManager foodManager = FoodManager.getInstance();
        FoodType subleft = new FoodType("subleft");
        FoodType subleft2 = new FoodType("subleft2");
        Set<FoodType> subs=new HashSet<FoodType>();
        subs.add(subleft);
        subs.add(subleft2);
        FoodType middle = new FoodType("middle");
        FoodType middle2 = new FoodType("middle2");
        FoodType middle3 = new FoodType("middle3");
        middle.setSubTypes(subs);
        subleft.setSuperType(middle);
        subleft2.setSuperType(middle);

        Set<FoodType> middles=new HashSet<>();
        middles.add(middle);
        middles.add(middle2);
        middles.add(middle3);
        FoodType top = new FoodType("top",null,middles);
        middle.setSuperType(top);
        middle2.setSuperType(top);
        middle3.setSuperType(top);

        Assert.assertTrue(top.isSubtype(middle));
        Assert.assertTrue(top.isSubtype(middle2));
        Assert.assertTrue(top.isSubtype(middle3));
        Assert.assertTrue(top.isSubtype(subleft));
        Assert.assertTrue(top.isSubtype(subleft2));
        Assert.assertFalse(middle2.isSubtype(subleft));
        Assert.assertFalse(middle3.isSubtype(subleft));
        Assert.assertTrue(middle.isSubtype(subleft2));
        Assert.assertTrue(top.isSubtype(subleft2));
    }

}
