package dev.alexguesser.invoice.application.usecase;

import java.io.PrintStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateInvoice {

    @Autowired
    private PrintStream out;

    public void execute(GenerateInvoiceInput input) {
        out.printf("Generating invoice for ride %s with amount %f%n", input.rideId(), input.amount());
    }

    public record GenerateInvoiceInput(
            UUID rideId,
            double amount
    ) {

    }

}
