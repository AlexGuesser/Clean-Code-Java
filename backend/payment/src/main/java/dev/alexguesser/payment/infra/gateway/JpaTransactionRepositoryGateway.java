package dev.alexguesser.payment.infra.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.payment.application.gateway.TransactionRepositoryGateway;
import dev.alexguesser.payment.domain.Transaction;
import dev.alexguesser.payment.infra.persistence.TransactionRepository;

@Component
public class JpaTransactionRepositoryGateway implements TransactionRepositoryGateway {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionEntityMapper transactionEntityMapper;


    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transactionEntityMapper.toEntity(transaction));
    }
}
