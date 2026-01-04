package com.fintech.payment.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentPostedEvent(
        UUID paymentId,
        UUID fromAccountId,
        UUID toAccountId,
        BigDecimal amount,
        Instant occurredAt
) {}
