package dev.alexguesser.ride.infra.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class RabbitMQConfig {

    static final String RIDE_REQUESTED_EXCHANGE = "rideRequested";
    static final String RIDE_ACCEPTED_EXCHANGE = "rideAccepted";
    static final String RIDE_COMPLETED_EXCHANGE = "rideCompleted";

    static final String UPDATE_PROJECTION_QUEUE = "updateProjection";
    static final String PROCESS_PAYMENT_QUEUE = "processPayment";
    static final String GENERATE_INVOICE_QUEUE = "generateInvoice";
    static final String SEND_RECEIPT_QUEUE = "sendReceipt";

    @Bean
    Queue updateProjectionQueue() {
        return new Queue(UPDATE_PROJECTION_QUEUE, true);
    }

    @Bean
    Queue processPaymentQueue() {
        return new Queue(PROCESS_PAYMENT_QUEUE, true);
    }

    @Bean
    Queue generateInvoiceQueue() {
        return new Queue(GENERATE_INVOICE_QUEUE, true);
    }

    @Bean
    Queue sendReceiptQueue() {
        return new Queue(SEND_RECEIPT_QUEUE, true);
    }

    @Bean
    FanoutExchange rideRequestedExchange() {
        return new FanoutExchange(RIDE_REQUESTED_EXCHANGE, true, false);
    }

    @Bean
    FanoutExchange rideAcceptedExchange() {
        return new FanoutExchange(RIDE_ACCEPTED_EXCHANGE, true, false);
    }

    @Bean
    FanoutExchange rideCompletedExchange() {
        return new FanoutExchange(RIDE_COMPLETED_EXCHANGE, true, false);
    }

    @Bean
    Binding rideRequestedUpdateProjectionBinding(@Qualifier("updateProjectionQueue") Queue updateProjectionQueue, @Qualifier("rideRequestedExchange") FanoutExchange rideRequestedExchange) {
        return BindingBuilder.bind(updateProjectionQueue).to(rideRequestedExchange);
    }

    @Bean
    Binding acceptedRideUpdateProjectionBinding(@Qualifier("updateProjectionQueue") Queue updateProjectionQueue, @Qualifier("rideAcceptedExchange") FanoutExchange rideAcceptedExchange) {
        return BindingBuilder.bind(updateProjectionQueue).to(rideAcceptedExchange);
    }

    @Bean
    Binding completedRideUpdateProjectionBinding(@Qualifier("updateProjectionQueue") Queue updateProjectionQueue, @Qualifier("rideCompletedExchange") FanoutExchange rideCompletedExchange) {
        return BindingBuilder.bind(updateProjectionQueue).to(rideCompletedExchange);
    }


    @Bean
    Binding processPaymentQueueBinding(@Qualifier("processPaymentQueue") Queue processPaymentQueue, @Qualifier("rideCompletedExchange") FanoutExchange rideCompletedExchange) {
        return BindingBuilder.bind(processPaymentQueue).to(rideCompletedExchange);
    }

    @Bean
    Binding generateInvoiceBinding(@Qualifier("generateInvoiceQueue") Queue generateInvoiceQueue, @Qualifier("rideCompletedExchange") FanoutExchange rideCompletedExchange) {
        return BindingBuilder.bind(generateInvoiceQueue).to(rideCompletedExchange);
    }

    @Bean
    Binding sendReceiptBinding(@Qualifier("sendReceiptQueue") Queue sendReceiptQueue, @Qualifier("rideCompletedExchange") FanoutExchange rideCompletedExchange) {
        return BindingBuilder.bind(sendReceiptQueue).to(rideCompletedExchange);
    }

    @Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    ApplicationRunner runner(ConnectionFactory cf) {
        return args -> cf.createConnection().close();
    }

}