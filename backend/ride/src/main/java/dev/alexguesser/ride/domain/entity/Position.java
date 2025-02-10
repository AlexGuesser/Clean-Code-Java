package dev.alexguesser.ride.domain.entity;

import static java.util.Objects.requireNonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import dev.alexguesser.ride.domain.vo.Coord;

public class Position {

    private UUID positionId;
    private UUID rideId;
    private Coord coord;
    private long createdAt;

    public Position(UUID positionId, UUID rideId, Coord coord, long createdAt) {
        this.positionId = requireNonNull(positionId);
        this.rideId = requireNonNull(rideId);
        this.coord = requireNonNull(coord);
        this.createdAt = createdAt;
    }

    public static Position create(UUID rideId, Coord coord) {
        return new Position(UUID.randomUUID(), rideId, coord, Instant.now().toEpochMilli());
    }

    public Position() {
    }

    public UUID getPositionId() {
        return positionId;
    }

    public UUID getRideId() {
        return rideId;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
