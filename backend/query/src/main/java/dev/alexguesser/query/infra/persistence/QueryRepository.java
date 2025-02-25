package dev.alexguesser.query.infra.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<QueryEntity, UUID> {
    QueryEntity findByRideId(UUID rideId);
}
