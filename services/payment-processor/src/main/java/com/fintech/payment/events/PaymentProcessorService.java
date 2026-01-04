package com.fintech.payment.service;

import com.fintech.payment.event.PaymentFailedEvent;
import com.fintech.payment.event.PaymentPostedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
public class PaymentProcessorService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentProcessorService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void processPayment(UUID from, UUID to, BigDecimal amount) {

        UUID paymentId = UUID.randomUUID();

        try {
            // Simulated business rules
            if (amount.signum() <= 0) {
                throw new IllegalArgumentException("Invalid amount");
            }

            PaymentPostedEvent event = new PaymentPostedEvent(
                    paymentId,
                    from,
                    to,
                    amount,
                    Instant.now()
            );

            kafkaTemplate.send("payment-events", event);

        } catch (Exception ex) {

            PaymentFailedEvent failedEvent = new PaymentFailedEvent(
                    paymentId,
                    ex.getMessage(),
                    Instant.now()
            );

            kafkaTemplate.send("payment-events", failedEvent);
        }
    }
}