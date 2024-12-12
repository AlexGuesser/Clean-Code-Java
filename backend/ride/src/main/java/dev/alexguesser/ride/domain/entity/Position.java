package dev.alexguesser.ride.domain.entity;

import java.util.UUID;

import dev.alexguesser.ride.domain.vo.Coord;

public class Position {

    private UUID positionId;
    private UUID rideId;
    private Coord coord;

    public Position(UUID positionId, UUID rideId, Coord coord) {
        this.positionId = positionId;
        this.rideId = rideId;
        this.coord = coord;
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
}
