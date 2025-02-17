package dev.alexguesser.ride.infra.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;


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
    DirectExchange rideRequestedExchange() {
        return new DirectExchange(RIDE_REQUESTED_EXCHANGE, true, false);
    }

    @Bean
    DirectExchange rideAcceptedExchange() {
        return new DirectExchange(RIDE_ACCEPTED_EXCHANGE, true, false);
    }

    @Bean
    DirectExchange rideCompletedExchange() {
        return new DirectExchange(RIDE_COMPLETED_EXCHANGE, true, false);
    }

    @Bean
    Binding updateProjectionBinding(@Qualifier("updateProjectionQueue") Queue updateProjectionQueue, @Qualifier("rideRequestedExchange") DirectExchange rideRequestedExchange) {
        return BindingBuilder.bind(updateProjectionQueue).to(rideRequestedExchange).with("");
    }

    @Bean
    Binding processPaymentQueueBinding(@Qualifier("processPaymentQueue") Queue processPaymentQueue, @Qualifier("rideCompletedExchange") DirectExchange rideCompletedExchange) {
        return BindingBuilder.bind(processPaymentQueue).to(rideCompletedExchange).with("");
    }

    @Bean
    Binding generateInvoiceBinding(@Qualifier("generateInvoiceQueue") Queue generateInvoiceQueue, @Qualifier("rideCompletedExchange") DirectExchange rideCompletedExchange) {
        return BindingBuilder.bind(generateInvoiceQueue).to(rideCompletedExchange).with("");
    }

    @Bean
    Binding sendReceiptBinding(@Qualifier("sendReceiptQueue") Queue sendReceiptQueue, @Qualifier("rideCompletedExchange") DirectExchange rideCompletedExchange) {
        return BindingBuilder.bind(sendReceiptQueue).to(rideCompletedExchange).with("");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(UPDATE_PROJECTION_QUEUE, PROCESS_PAYMENT_QUEUE, GENERATE_INVOICE_QUEUE, SEND_RECEIPT_QUEUE);
        container.setMessageListener(message -> {});
        return container;
    }
}