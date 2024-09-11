package Entities;

import Entities.enums.ConsumptionType;
import Entities.enums.FoodType;
import Entities.enums.TransportType;

import java.time.LocalDate;

public class Transport extends  CarbonConsumption{

    private TransportType transportType;
    private Double distance;
    private Double impact;

    public Transport(Double volume, Double distanceTravelled, TransportType transportType, LocalDate startDate, LocalDate endDate, Integer userId) {
        super(volume, startDate, endDate, userId, ConsumptionType.TRANSPORT);
        this.transportType = transportType;
        this.distance = distanceTravelled;
        setImpact(transportType);
    }

    public Double getImpact() {
        return impact;
    }

    public void setImpact(TransportType impact) {
        switch (impact){
            case TRAIN:
                this.impact = 0.1;
                break;
            case VEHICLE:
                this.impact = 0.5;
                break;
        };
    }


    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public Double calculateImpact() {
        return getVolume() * getDistance() * getImpact();
    }
}
