package dev.alexguesser.payment.application.gateway;

import dev.alexguesser.payment.domain.Transaction;

public interface TransactionRepositoryGateway {

    void saveTransaction(Transaction transaction);

}
