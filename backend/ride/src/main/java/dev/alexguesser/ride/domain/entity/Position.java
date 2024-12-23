package dev.alexguesser.ride.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import dev.alexguesser.ride.domain.vo.Coord;

public class Position {

    private UUID positionId;
    private UUID rideId;
    private Coord coord;
    private LocalDateTime date;

    public Position(UUID positionId, UUID rideId, Coord coord, LocalDateTime date) {
        this.positionId = positionId;
        this.rideId = rideId;
        this.coord = coord;
        this.date = date;
    }

    static Position create(UUID rideId, Coord coord) {
        return new Position(UUID.randomUUID(), rideId, coord, LocalDateTime.now());
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

    public LocalDateTime getDate() {
        return date;
    }
}
