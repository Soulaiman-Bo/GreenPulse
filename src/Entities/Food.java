package Entities;

import Entities.enums.ConsumptionType;
import Entities.enums.FoodType;

import java.time.LocalDate;

public class Food extends CarbonConsumption{

    private FoodType foodType;
    private Double weight;

    public Food(Double volume, FoodType typeFood, Double weight, LocalDate startDate, LocalDate endDate, Integer userId) {
        super(volume, startDate, endDate, userId, ConsumptionType.FOOD);
        this.foodType = typeFood;
        this.weight = weight;
    }

    public Food(Double volume, LocalDate startDate, LocalDate endDate, Integer userId, ConsumptionType consumptionType) {
        super(volume, startDate, endDate, userId, consumptionType);
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public Integer calculateImpact() {
        return 0;
    }
}
