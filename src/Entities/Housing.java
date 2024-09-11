package Entities;

import Entities.enums.ConsumptionType;
import Entities.enums.FoodType;
import Entities.enums.HousingType;

import java.time.LocalDate;

public class Housing extends CarbonConsumption {

    private Double energyConsumption;
    private HousingType housingType;
    private Double impact;

    public Housing(Double volume, Double energyConsumption, HousingType typeEnergy, LocalDate startDate, LocalDate endDate, Integer userId) {
        super(volume, startDate, endDate, userId, ConsumptionType.HOUSING);
        this.energyConsumption = energyConsumption;
        this.housingType = typeEnergy;
        setImpact(typeEnergy);
    }

    public Double getImpact() {
        return impact;
    }

    public void setImpact(HousingType impact) {
        switch (impact){
            case GAS:
                this.impact = 2.0;
                break;
            case ELECTRICITY:
                this.impact = 1.5;
                break;
        };
    }


    public Double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public HousingType getHousingType() {
        return housingType;
    }

    public void setHousingType(HousingType housingType) {
        this.housingType = housingType;
    }

    @Override
    public Double calculateImpact() {
        return getVolume() * getImpact() * getEnergyConsumption();
    }
}
