package dev.alexguesser.payment.infra.fallback;

import dev.alexguesser.payment.infra.gateway.PJBankPaymentGateway;

public class PJBankPaymentProcessor extends PaymentProcessor {

    protected PJBankPaymentProcessor(PaymentProcessor next, PJBankPaymentGateway pjBankPaymentGateway) {
        super(next, pjBankPaymentGateway);
    }


}
