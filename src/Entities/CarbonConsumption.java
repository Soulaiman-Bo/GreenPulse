package Entities;

import Entities.enums.ConsumptionType;

import java.time.LocalDate;

public abstract class CarbonConsumption {

    private Double volume;
    private LocalDate startDate;
    private LocalDate endDate;
    private int userId;

    private ConsumptionType consumptionType;

    public CarbonConsumption(Double volume, LocalDate startDate, LocalDate endDate, Integer userId, ConsumptionType consumptionType) {
        this.volume = volume;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.consumptionType = consumptionType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ConsumptionType getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(ConsumptionType consumptionType) {
        this.consumptionType = consumptionType;
    }

    public abstract Integer calculateImpact();

    @Override
    public String toString() {
        return "CarbonConsumption{" +
                "volume=" + volume +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", userId=" + userId +
                ", consumptionType=" + consumptionType +
                '}';
    }
}
