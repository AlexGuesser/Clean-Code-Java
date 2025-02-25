package dev.alexguesser.query.infra.persistence;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "query")
public class QueryEntity {

    @Id
    @Column(name = "ride_id")
    private UUID rideId;
    @Column(name = "passenger_name")
    private String passengerName;
    @Column(name = "driver_name")
    private String driverName;
    @Column(name = "fare")
    private Double fare;
    @Column(name = "distance")
    private Double distance;

    public QueryEntity() {
    }

    public QueryEntity(UUID rideId, String passengerName, String driverName, Double fare, Double distance) {
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

    public Double getFare() {
        return fare;
    }

    public Double getDistance() {
        return distance;
    }
}
