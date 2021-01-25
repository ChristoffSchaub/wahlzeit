package org.wahlzeit.model;

import java.util.*;


public class FoodType  {
    public FoodManager manager;
    private String id;
    private String foodTypeName;
    protected FoodType superType = null;
    protected Set<FoodType> subTypes = new HashSet<FoodType>();

    public FoodType(String foodTypeName) {
        this.manager = FoodManager.getInstance();
        this.foodTypeName = foodTypeName;
        this.id = foodTypeName;
        this.manager.getFoodTypes().put(this.id,this);
    }

    public FoodType(String foodTypeName, FoodType superType, Set<FoodType> subTypes) {
        this.manager = FoodManager.getInstance();
        this.foodTypeName = foodTypeName;
        this.id = foodTypeName;
        this.superType = superType;
        this.subTypes = subTypes;
        this.manager.getFoodTypes().put(this.id,this);
    }


    public FoodManager getManager() {
        return manager;
    }


    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
    }

    public FoodType getSuperType() {
        return superType;
    }

    public void setSuperType(FoodType foodType) {
        this.superType = foodType;
    }

    public Set<FoodType> getSubTypes() {
        return subTypes;
    }

    public Iterator<FoodType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    public void setSubTypes(Set<FoodType> subTypes) {
        this.subTypes = subTypes;
    }

    public void addSubType(FoodType ft) {
        assert (ft != null) : "tried to set null sub-type";
        ft.setSuperType(this);
        subTypes.add(ft);
    }

    public boolean isSubtype(FoodType type) {
        assert (type != null);
        if (type.foodTypeName == this.foodTypeName)
            return true;
        for (FoodType foodType :
                this.getSubTypes()) {
            if (foodType.isSubtype(type))
                return true;
        }
        return false;
    }

    public Food createInstance(int calories) {
        return new Food(this, calories);
    }

    public boolean hasInstance(Food food) {
        assert (food != null) : "asked about null object";
        if (food.getType() == this) {
            return true;
        }
        for (FoodType type : subTypes) {
            if (type.hasInstance(food)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodType)) return false;
        FoodType foodType = (FoodType) o;
        return Objects.equals(getManager(), foodType.getManager()) &&
                Objects.equals(getFoodTypeName(), foodType.getFoodTypeName()) &&
                Objects.equals(getSuperType(), foodType.getSuperType()) &&
                Objects.equals(getSubTypes(), foodType.getSubTypes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getManager(), getFoodTypeName());
    }

    public String getId() {
        return this.id;
    }
}
