package dev.alexguesser.payment.application.usecase.input;

import java.math.BigDecimal;
import java.util.UUID;

public record ProcessPaymentInput(
        UUID rideId,
        BigDecimal amount
) {
}
