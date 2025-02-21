package dev.alexguesser.payment.domain;

import static dev.alexguesser.payment.domain.TransactionStatus.PAID;
import static dev.alexguesser.payment.domain.TransactionStatus.WAITING_PAYMENT;
import static java.util.UUID.randomUUID;

import java.time.Instant;
import java.util.UUID;

public class Transaction {

    private final UUID transactionId;
    private final UUID rideId;
    private final double amount;
    private final long timestamp;
    private String status;

    public Transaction(UUID transactionId, UUID rideId, double amount, long timestamp, String status) {
        this.transactionId = transactionId;
        this.rideId = rideId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
    }

    public static Transaction create(UUID rideId, double amount) {
        return new Transaction(
                randomUUID(),
                rideId,
                amount,
                Instant.now().toEpochMilli(),
                WAITING_PAYMENT.getValue()
        );
    }

    public void pay() {
        this.status = PAID.getValue();
    }

    public String getStatus() {
        return this.status;
    }

}
