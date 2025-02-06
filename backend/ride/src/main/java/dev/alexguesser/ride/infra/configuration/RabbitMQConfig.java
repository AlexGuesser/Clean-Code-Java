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

    static final String RIDE_REQUESTED_QUEUE = "rideRequested.updateProjection";
    static final String RIDE_ACCEPTED_QUEUE = "rideAccepted.updateProjection";
    static final String RIDE_COMPLETED_UPDATE_QUEUE = "rideCompleted.updateProjection";
    static final String RIDE_COMPLETED_PAYMENT_QUEUE = "rideCompleted.processPayment";
    static final String RIDE_COMPLETED_INVOICE_QUEUE = "rideCompleted.generateInvoice";
    static final String RIDE_COMPLETED_RECEIPT_QUEUE = "rideCompleted.sendReceipt";

    @Bean
    Queue rideRequestedQueue() {
        return new Queue(RIDE_REQUESTED_QUEUE, true);
    }

    @Bean
    Queue rideAcceptedQueue() {
        return new Queue(RIDE_ACCEPTED_QUEUE, true);
    }

    @Bean
    Queue rideCompletedUpdateQueue() {
        return new Queue(RIDE_COMPLETED_UPDATE_QUEUE, true);
    }

    @Bean
    Queue rideCompletedPaymentQueue() {
        return new Queue(RIDE_COMPLETED_PAYMENT_QUEUE, true);
    }

    @Bean
    Queue rideCompletedInvoiceQueue() {
        return new Queue(RIDE_COMPLETED_INVOICE_QUEUE, true);
    }

    @Bean
    Queue rideCompletedReceiptQueue() {
        return new Queue(RIDE_COMPLETED_RECEIPT_QUEUE, true);
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
    Binding rideRequestedBinding(@Qualifier("rideRequestedQueue") Queue rideRequestedQueue, @Qualifier("rideRequestedExchange") DirectExchange rideRequestedExchange) {
        return BindingBuilder.bind(rideRequestedQueue).to(rideRequestedExchange).with("");
    }

    @Bean
    Binding rideAcceptedBinding(@Qualifier("rideAcceptedQueue") Queue rideAcceptedQueue, @Qualifier("rideAcceptedExchange") DirectExchange rideAcceptedExchange) {
        return BindingBuilder.bind(rideAcceptedQueue).to(rideAcceptedExchange).with("");
    }

    @Bean
    Binding rideCompletedUpdateBinding(@Qualifier("rideCompletedUpdateQueue") Queue rideCompletedUpdateQueue, @Qualifier("rideCompletedExchange") DirectExchange rideCompletedExchange) {
        return BindingBuilder.bind(rideCompletedUpdateQueue).to(rideCompletedExchange).with("");
    }

    @Bean
    Binding rideCompletedPaymentBinding(@Qualifier("rideCompletedPaymentQueue") Queue rideCompletedPaymentQueue, @Qualifier("rideCompletedExchange") DirectExchange rideCompletedExchange) {
        return BindingBuilder.bind(rideCompletedPaymentQueue).to(rideCompletedExchange).with("");
    }

    @Bean
    Binding rideCompletedInvoiceBinding(@Qualifier("rideCompletedInvoiceQueue") Queue rideCompletedInvoiceQueue, @Qualifier("rideCompletedExchange") DirectExchange rideCompletedExchange) {
        return BindingBuilder.bind(rideCompletedInvoiceQueue).to(rideCompletedExchange).with("");
    }

    @Bean
    Binding rideCompletedReceiptBinding(@Qualifier("rideCompletedReceiptQueue") Queue rideCompletedReceiptQueue, @Qualifier("rideCompletedExchange") DirectExchange rideCompletedExchange) {
        return BindingBuilder.bind(rideCompletedReceiptQueue).to(rideCompletedExchange).with("");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RIDE_REQUESTED_QUEUE, RIDE_ACCEPTED_QUEUE, RIDE_COMPLETED_UPDATE_QUEUE, RIDE_COMPLETED_PAYMENT_QUEUE, RIDE_COMPLETED_INVOICE_QUEUE, RIDE_COMPLETED_RECEIPT_QUEUE);
        return container;
    }
}