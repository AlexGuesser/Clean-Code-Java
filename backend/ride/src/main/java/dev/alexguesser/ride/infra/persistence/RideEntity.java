package dev.alexguesser.ride.infra.persistence;

import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ride")
public class RideEntity {

    @Id
    @Column(name = "ride_id")
    private UUID rideId;
    @Column(name = "passenger_id")
    private UUID passengerId;
    @Nullable
    @Column(name = "driver_id", nullable = true)
    private UUID driverId;
    @Column(name = "from_long")
    private Double fromLongitude;
    @Column(name = "from_lat")
    private Double fromLatitude;
    @Column(name = "to_long")
    private Double toLongitude;
    @Column(name = "to_lat")
    private Double toLatitude;
    private String status;
    @Column(name = "created_at")
    private long createdAt;
    @Column(name = "straight_distance")
    private double straightDistance;
    @Column(name = "fare")
    private Double fare;
    @Column(name = "distance")
    private Double distance;

    public RideEntity() {
    }

    public RideEntity(UUID rideId, UUID passengerId, @Nullable UUID driverId, Double fromLongitude, Double fromLatitude, Double toLongitude, Double toLatitude, String status, long createdAt, Double straightDistance, Double fare, Double distance) {
        this.rideId = rideId;
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.fromLongitude = fromLongitude;
        this.fromLatitude = fromLatitude;
        this.toLongitude = toLongitude;
        this.toLatitude = toLatitude;
        this.status = status;
        this.createdAt = createdAt;
        this.straightDistance = straightDistance;
        this.fare = fare;
        this.distance = distance;
    }

    public UUID getRideId() {
        return rideId;
    }

    public UUID getPassengerId() {
        return passengerId;
    }

    @Nullable
    public UUID getDriverId() {
        return driverId;
    }

    public Double getFromLongitude() {
        return fromLongitude;
    }

    public Double getFromLatitude() {
        return fromLatitude;
    }

    public Double getToLongitude() {
        return toLongitude;
    }

    public Double getToLatitude() {
        return toLatitude;
    }

    public String getStatus() {
        return status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public double getStraightDistance() {
        return straightDistance;
    }

    public Double getFare() {
        return fare;
    }

    public Double getDistance() {
        return distance;
    }
}
