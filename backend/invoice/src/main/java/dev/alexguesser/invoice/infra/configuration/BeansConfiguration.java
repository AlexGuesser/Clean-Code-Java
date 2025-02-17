package dev.alexguesser.invoice.infra.configuration;

import java.io.PrintStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public PrintStream invoiceQueue() {
        return System.out;
    }
}
