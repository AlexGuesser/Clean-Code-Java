package dev.alexguesser.invoice.application.usecase;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateInvoice {

    @Autowired
    private PrintStream out;

    public void execute(Object input) {
        out.printf("Generating invoice for ride %s", (String) input);
    }

    public record GenerateInvoiceInput(
            UUID rideId,
            BigDecimal amount
    ) {

    }

}
