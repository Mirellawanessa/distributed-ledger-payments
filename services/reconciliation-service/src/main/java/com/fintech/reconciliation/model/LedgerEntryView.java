package com.fintech.reconciliation.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record LedgerEntryView(
        UUID transactionId,
        UUID accountId,
        BigDecimal amount,
        String type, // DEBIT | CREDIT
        Instant occurredAt
) {}
