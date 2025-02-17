package dev.alexguesser.ride.application.usecase;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.alexguesser.ride.application.usecase.input.RequestRideInput;
import dev.alexguesser.ride.infra.gateway.AccountGateway;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class RequestRideTest {

    @MockBean
    private AccountGateway accountGateway;

    @Autowired
    private RequestRide requestRide;

    @Autowired
    private GetRide getRide;

    private final AccountGateway.AccountDetailDto passengerAccount = new AccountGateway.AccountDetailDto(
            UUID.randomUUID().toString(),
            "John Doe",
            "joh.doe@email.com",
            "123.456.789-09",
            null,
            true,
            false
    );

    private final AccountGateway.AccountDetailDto notPassengerAccount = new AccountGateway.AccountDetailDto(
            UUID.randomUUID().toString(),
            "John Doe",
            "joh.doe@email.com",
            "123.456.789-09",
            null,
            false,
            true
    );

    @Test
    void requestRide_throwsEntityNotFoundException_whenAccountIsNotFoundById() {
        assertThatThrownBy(
                () -> requestRide.execute(new RequestRideInput(
                        UUID.randomUUID(),
                        0.0,
                        0.0,
                        0.0,
                        0.0
                ))
        ).isInstanceOf(EntityNotFoundException.class);

    }

}