package dev.alexguesser.payment.infra.fallback;

import dev.alexguesser.payment.infra.gateway.CieloPaymentGateway;

public class CieloPaymentProcessor extends PaymentProcessor {

    protected CieloPaymentProcessor(PaymentProcessor next, CieloPaymentGateway cieloPaymentGateway) {
        super(next, cieloPaymentGateway);
    }


}
