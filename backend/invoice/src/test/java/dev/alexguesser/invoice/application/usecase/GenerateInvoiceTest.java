package dev.alexguesser.invoice.application.usecase;

import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class GenerateInvoiceTest {

    @Autowired
    private GenerateInvoice generateInvoice;

    @MockBean
    private PrintStream out;

    @Test
    void executePrintsCorrectMessageForValidInput() {
        UUID rideId = UUID.randomUUID();
        BigDecimal amount = new BigDecimal("100.00");
        GenerateInvoice.GenerateInvoiceInput input = new GenerateInvoice.GenerateInvoiceInput(rideId, amount);

        generateInvoice.execute(input);

        verify(out).printf("Generating invoice for ride %s with amount %s%n", rideId, amount);
    }

    @Test
    void executeHandlesNegativeAmount() {
        UUID rideId = UUID.randomUUID();
        BigDecimal amount = new BigDecimal("-100.00");
        GenerateInvoice.GenerateInvoiceInput input = new GenerateInvoice.GenerateInvoiceInput(rideId, amount);

        generateInvoice.execute(input);

        verify(out).printf("Generating invoice for ride %s with amount %s%n", rideId, amount);
    }
}