package dev.alexguesser.payment.application.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.payment.application.gateway.TransactionRepositoryGateway;
import dev.alexguesser.payment.application.usecase.input.ProcessPaymentInput;
import dev.alexguesser.payment.domain.Transaction;
import dev.alexguesser.payment.infra.fallback.PaymentProcessor;
import dev.alexguesser.payment.infra.fallback.PaymentProcessorFactory;
import dev.alexguesser.payment.infra.gateway.PaymentGateway;

@Component
public class ProcessPayment {

    private static final Logger logger = LoggerFactory.getLogger(ProcessPayment.class);

    @Autowired
    private PaymentProcessorFactory paymentProcessorFactory;

    @Autowired
    private TransactionRepositoryGateway transactionRepositoryGateway;

    public void execute(ProcessPaymentInput input) {
        logger.info("Processing payment for transaction {}", input);
        PaymentProcessor paymentProcessor = paymentProcessorFactory.create();
        Transaction transaction = Transaction.create(input.rideId(), input.amount());
        PaymentGateway.Input gatewayInput = getSamplePaymentGatewayInput(input);
        try {
            PaymentGateway.Output output = paymentProcessor.process(gatewayInput);
            if(output.success()) {
                transaction.pay();
                transactionRepositoryGateway.saveTransaction(transaction);
            }
        } catch (Exception e) {
            logger.error("Error processing payment", e);
        }

    }

    private PaymentGateway.Input getSamplePaymentGatewayInput(ProcessPaymentInput input) {
        return new PaymentGateway.Input(
                "Example Client",
                "4012001037141112",
                "12/2023",
                "123",
                input.amount()
        );
    }
}
