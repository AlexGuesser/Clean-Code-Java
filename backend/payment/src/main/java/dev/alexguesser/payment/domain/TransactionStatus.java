package dev.alexguesser.payment.domain;

public enum TransactionStatus {

    WAITING_PAYMENT("waiting_payment"), PAID("paid");

    private String value;

    TransactionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
