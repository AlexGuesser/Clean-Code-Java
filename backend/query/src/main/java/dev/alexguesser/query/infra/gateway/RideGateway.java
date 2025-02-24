package dev.alexguesser.query.infra.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
public class RideGateway {

    @Autowired
    private RestClient restClient;

    public GetRideDto getRideById(String id) {
        return
                restClient.get()
                        .uri("http://localhost:8081/ride/" + id)
                        .retrieve()
                        .body(GetRideDto.class);
    }

    @JsonIgnoreProperties
    public record GetRideDto(
            String rideId,
            String passengerId,
            String driverId,
            double fare,
            double distance
    ) {
    }

}

