package com.fintech.audit.model;

import java.time.Instant;
import java.util.UUID;

public record AuditRecord(
        UUID eventId,
        String eventType,
        String payload,
        Instant recordedAt
) {}
