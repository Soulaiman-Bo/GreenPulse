package Entities;

import Entities.enums.ConsumptionType;
import Entities.enums.HousingType;

import java.time.LocalDate;

public class Housing extends CarbonConsumption {

    private Integer energyConsumption;
    private HousingType housingType;

    public Housing(Double volume, Integer energyConsumption, HousingType typeEnergy, LocalDate startDate, LocalDate endDate, Integer userId) {
        super(volume, startDate, endDate, userId, ConsumptionType.HOUSING);
        this.energyConsumption = energyConsumption;
        this.housingType = typeEnergy;
    }

    public Housing(Double volume, LocalDate startDate, LocalDate endDate, Integer userId, ConsumptionType consumptionType) {
        super(volume, startDate, endDate, userId, consumptionType);
    }

    public Integer getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Integer energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public HousingType getHousingType() {
        return housingType;
    }

    public void setHousingType(HousingType housingType) {
        this.housingType = housingType;
    }

    @Override
    public Integer calculateImpact() {
        return 0;
    }
}
