package dev.alexguesser.payment.infra.fallback;


import org.springframework.lang.Nullable;

import dev.alexguesser.payment.infra.gateway.PaymentGateway;

// Chain of Responsibility pattern
public abstract class PaymentProcessor {

    @Nullable
    private final PaymentProcessor next;

    private final PaymentGateway paymentGateway;

    public PaymentGateway.Output process(PaymentGateway.Input input) throws RuntimeException {
        try {
            return paymentGateway.process(input);
        } catch (Exception e) {
            if (next == null) {
                throw new RuntimeException("No processor available");
            }
            return next.process(input);
        }
    }

    protected PaymentProcessor(@Nullable PaymentProcessor next, PaymentGateway paymentGateway) {
        this.next = next;
        this.paymentGateway = paymentGateway;
    }
}
