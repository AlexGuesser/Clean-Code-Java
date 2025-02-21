package dev.alexguesser.payment.infra.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "ride_id")
    private String rideId;

    private double amount;

    private long timestamp;

    private String status;

    public TransactionEntity() {
    }

    public TransactionEntity(String transactionId, String rideId, double amount, long timestamp, String status) {
        this.transactionId = transactionId;
        this.rideId = rideId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getRideId() {
        return rideId;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }
}
