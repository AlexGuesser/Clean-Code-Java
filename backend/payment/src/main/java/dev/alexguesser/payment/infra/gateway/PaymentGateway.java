package dev.alexguesser.payment.infra.gateway;

public interface PaymentGateway {

    Output process(Input input);

    record Input(
            String cardHolder,
            String creditCardNumber,
            String expDate,
            String cvv,
            double amount
    ) {
    }

    record Output(
            String tid,
            String authorizationCode,
            String status
    ) {
        public boolean success() {
            return "APPROVED".equalsIgnoreCase(status);
        }
    }

}

