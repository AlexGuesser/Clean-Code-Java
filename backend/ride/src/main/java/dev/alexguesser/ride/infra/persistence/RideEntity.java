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
    private Integer fromLongitude;
    @Column(name = "from_lat")
    private Integer fromLatitude;
    @Column(name = "to_long")
    private Integer toLongitude;
    @Column(name = "to_lat")
    private Integer toLatitude;
    private String status;
    @Column(name = "created_at")
    private long createdAt;

    public RideEntity() {
    }

    public RideEntity(UUID rideId, UUID passengerId, @Nullable UUID driverId, Integer fromLongitude, Integer fromLatitude, Integer toLongitude, Integer toLatitude, String status, long createdAt) {
        this.rideId = rideId;
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.fromLongitude = fromLongitude;
        this.fromLatitude = fromLatitude;
        this.toLongitude = toLongitude;
        this.toLatitude = toLatitude;
        this.status = status;
        this.createdAt = createdAt;
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

    public Integer getFromLongitude() {
        return fromLongitude;
    }

    public Integer getFromLatitude() {
        return fromLatitude;
    }

    public Integer getToLongitude() {
        return toLongitude;
    }

    public Integer getToLatitude() {
        return toLatitude;
    }

    public String getStatus() {
        return status;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
