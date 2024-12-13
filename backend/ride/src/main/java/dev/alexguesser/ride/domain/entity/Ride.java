package dev.alexguesser.ride.domain.entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import dev.alexguesser.ride.domain.DistanceCalculator;
import dev.alexguesser.ride.domain.event.RideCompletedEvent;
import dev.alexguesser.ride.domain.service.FareCalculatorFactory;
import dev.alexguesser.ride.domain.vo.Coord;
import dev.alexguesser.ride.domain.vo.rideStatus.RideStatus;
import dev.alexguesser.ride.infra.mediator.Mediator;
import jakarta.annotation.Nullable;

public class Ride extends Mediator {

    private UUID rideId;
    private UUID passengerId;
    @Nullable
    private UUID driverId;
    private Coord from;
    private Coord to;
    private RideStatus status;
    private long createdAt;
    private float distance = 0;
    private float fare = 0;

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

    public void setDriverId(@Nullable UUID driverId) {
        this.driverId = driverId;
    }

    public void accept(UUID driverId) {
        this.status.accept();
        setDriverId(driverId);
    }

    public void finish(List<Position> positions) {
        this.distance = 0;
        this.fare = 0;
        for (int i = 0; i < positions.size() - 1; i++) {
            Position position = positions.get(i);
            Position nextPosition = positions.get(i + 1);
            float distance = DistanceCalculator.calculate(position.getCoord(), nextPosition.getCoord());
            this.distance += distance;
            this.fare += FareCalculatorFactory.getFareCalculator(position.getDate()).calculate(distance);
        }
        this.status.finish();
        RideCompletedEvent event = new RideCompletedEvent(this.getRideId(), this.fare, this.distance);
        this.notify(RideCompletedEvent.eventName, event);
    }
}
