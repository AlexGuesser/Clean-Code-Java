package dev.alexguesser.ride;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableRabbit
public class RideApi {
    public static void main(String[] args) {
        SpringApplication.run(RideApi.class, args);
    }
}