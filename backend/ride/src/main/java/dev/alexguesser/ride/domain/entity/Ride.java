package dev.alexguesser.ride.domain.entity;

import java.time.Instant;
import java.util.UUID;

import dev.alexguesser.ride.domain.vo.Coord;
import dev.alexguesser.ride.domain.vo.rideStatus.RideStatus;
import jakarta.annotation.Nullable;

public class Ride {

    private UUID rideId;
    private UUID passengerId;
    @Nullable
    private UUID driverId;
    private Coord from;
    private Coord to;
    private RideStatus status;
    private long createdAt;

    public Ride(
            UUID rideId,
            UUID passengerId,
            @Nullable UUID driverId,
            Coord from,
            Coord to,
            String status,
            long createdAt
    ) {
        this.rideId = rideId;
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.from = from;
        this.to = to;
        this.status = RideStatus.RideStatusFactory.create(status, this);
        this.createdAt = createdAt;
    }

    public Ride create(
            UUID passengerId,
            Coord from,
            Coord to
    ) {
        return new Ride(
                UUID.randomUUID(),
                passengerId,
                null,
                from,
                to,
                "requested",
                Instant.now().toEpochMilli()
        );
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

    public Coord getFrom() {
        return from;
    }

    public Coord getTo() {
        return to;
    }

    public RideStatus getStatus() {
        return status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }
}
