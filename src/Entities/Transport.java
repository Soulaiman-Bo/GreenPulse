package Entities;

import Entities.enums.ConsumptionType;
import Entities.enums.TransportType;

import java.time.LocalDate;

public class Transport extends  CarbonConsumption{

    private TransportType transportType;
    private Integer distance;

    public Transport(Double volume, Integer distanceTravelled, TransportType transportType, LocalDate startDate, LocalDate endDate, Integer userId) {
        super(volume, startDate, endDate, userId, ConsumptionType.TRANSPORT);
        this.transportType = transportType;
        this.distance = distanceTravelled;
    }

    public Transport(Double volume, LocalDate startDate, LocalDate endDate, Integer userId, ConsumptionType consumptionType) {
        super(volume, startDate, endDate, userId, consumptionType);
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public Integer calculateImpact() {
        return 0;
    }
}
