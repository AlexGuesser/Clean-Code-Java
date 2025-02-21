package dev.alexguesser.payment.infra.fallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.payment.infra.gateway.CieloPaymentGateway;
import dev.alexguesser.payment.infra.gateway.PJBankPaymentGateway;

@Component
public class PaymentProcessorFactory {

    @Autowired
    private CieloPaymentGateway cieloPaymentGateway;

    @Autowired
    private PJBankPaymentGateway pjBankPaymentGateway;

    public PaymentProcessor create() {
        CieloPaymentProcessor cieloPaymentProcessor = new CieloPaymentProcessor(null, cieloPaymentGateway);
        return new PJBankPaymentProcessor(cieloPaymentProcessor, pjBankPaymentGateway);
    }


}
