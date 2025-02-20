package dev.alexguesser.ride.infra.persistence;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "position")
public class PositionEntity {

    @Id
    @Column(name = "position_id")
    private UUID positionId;
    @Column(name = "ride_id")
    private UUID rideId;
    @Column(name = "lat")
    private double latitude;
    @Column(name = "long")
    private double longitude;
    @Column(name = "created_at")
    private long createdAt;

    public PositionEntity() {
    }

    public PositionEntity(UUID positionId, UUID rideId, double latitude, double longitude, long createdAt) {
        this.positionId = positionId;
        this.rideId = rideId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
    }

    public UUID getPositionId() {
        return positionId;
    }

    public UUID getRideId() {
        return rideId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
