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
    private TransactionStatus status;

    public Transaction(UUID transactionId, UUID rideId, double amount, long timestamp, TransactionStatus status) {
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
                WAITING_PAYMENT
        );
    }

    public void paid() {
        this.status = PAID;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public UUID getRideId() {
        return rideId;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public TransactionStatus getStatus() {
        return status;
    }
}
