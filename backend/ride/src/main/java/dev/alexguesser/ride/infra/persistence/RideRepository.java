package dev.alexguesser.ride.infra.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<RideEntity, UUID> {
    @Query(
            value = "select count(*)>0 from ccca.ride where passenger_id = :passengerId and status not in ('finished', 'cancelled')",
            nativeQuery = true
    )
    boolean hasActiveRideByPassengerId(@Param("passengerId") UUID passengerId);
}
