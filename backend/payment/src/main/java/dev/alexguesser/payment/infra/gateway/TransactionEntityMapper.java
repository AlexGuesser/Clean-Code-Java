package dev.alexguesser.payment.infra.gateway;

import java.util.UUID;

import org.springframework.stereotype.Component;

import dev.alexguesser.payment.domain.Transaction;
import dev.alexguesser.payment.domain.TransactionStatus;
import dev.alexguesser.payment.infra.persistence.TransactionEntity;

@Component
public class TransactionEntityMapper {

    public TransactionEntity toEntity(Transaction transaction) {
        return new TransactionEntity(
                transaction.getTransactionId().toString(),
                transaction.getRideId().toString(),
                transaction.getAmount(),
                transaction.getTimestamp(),
                transaction.getStatus().getValue()
        );
    }

    public Transaction toDomain(TransactionEntity transactionEntity) {
        return new Transaction(
                UUID.fromString(transactionEntity.getTransactionId()),
                UUID.fromString(transactionEntity.getRideId()),
                transactionEntity.getAmount(),
                transactionEntity.getTimestamp(),
                TransactionStatus.valueOf(transactionEntity.getStatus())
        );
    }


}
