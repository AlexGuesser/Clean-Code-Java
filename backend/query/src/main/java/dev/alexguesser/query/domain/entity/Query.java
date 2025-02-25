package dev.alexguesser.query.domain.entity;

import java.util.UUID;

public class Query {

    private final UUID rideId;
    private final String passengerName;
    private String driverName;
    private Double fare;
    private Double distance;

    public Query(UUID rideId, String passengerName) {
        this.rideId = rideId;
        this.passengerName = passengerName;
    }

    public Query(UUID rideId, String passengerName, String driverName, Double fare, Double distance) {
        this.rideId = rideId;
        this.passengerName = passengerName;
        this.driverName = driverName;
        this.fare = fare;
        this.distance = distance;
    }

    public UUID getRideId() {
        return rideId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
