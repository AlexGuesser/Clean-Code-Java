package dev.alexguesser.payment.application.usecase.input;

import java.util.UUID;

public record ProcessPaymentInput(
        UUID rideId,
        double amount
) {
}
