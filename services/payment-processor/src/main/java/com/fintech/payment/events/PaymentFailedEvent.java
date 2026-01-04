package com.fintech.payment.event;

import java.time.Instant;
import java.util.UUID;

public record PaymentFailedEvent(
        UUID paymentId,
        String reason,
        Instant occurredAt
) {}
