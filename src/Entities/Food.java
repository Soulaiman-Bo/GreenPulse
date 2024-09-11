package Entities;

import Entities.enums.ConsumptionType;
import Entities.enums.FoodType;

import java.time.LocalDate;

public class Food extends CarbonConsumption{

    private FoodType foodType;
    private Double weight;
    private Double impact;

    public Food(Double volume, FoodType typeFood, Double weight, LocalDate startDate, LocalDate endDate, Integer userId) {
        super(volume, startDate, endDate, userId, ConsumptionType.FOOD);
        this.foodType = typeFood;
        this.weight = weight;
        setImpact(typeFood);
    }

    public Double getImpact() {
        return impact;
    }

    public void setImpact(FoodType impact) {
        switch (impact){
            case MEAT:
                    this.impact = 5.0;
                break;
            case VEGETABLES:
                    this.impact = 0.5;
                break;
        };
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
    public Double calculateImpact() {
        return  weight * getVolume() * getImpact();
    }
}
