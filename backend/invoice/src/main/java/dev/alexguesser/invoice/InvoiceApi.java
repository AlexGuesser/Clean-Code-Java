package dev.alexguesser.invoice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class InvoiceApi {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceApi.class, args);
    }

}
