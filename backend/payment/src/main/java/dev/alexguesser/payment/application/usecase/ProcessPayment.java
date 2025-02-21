package dev.alexguesser.payment.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.payment.application.usecase.input.ProcessPaymentInput;
import dev.alexguesser.payment.infra.fallback.PaymentProcessorFactory;

@Component
public class ProcessPayment {

    @Autowired
    private PaymentProcessorFactory paymentProcessorFactory;


    public void execute(ProcessPaymentInput input) {

    }
}
