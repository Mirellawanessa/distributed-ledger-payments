package com.fintech.audit;

import org.people.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AuditEventConsumer {

    @KafkaListener(
        topics = "ledger-events",
        groupId = "audit"
    )
    public void audit(LedgerEntryCreatedEvent event) {

        // 1. Persist event (e.g., PostgreSQL, S3, Elasticsearch, etc.)
        // 2. Ensure immutability of audit records
        // 3. Support replay and future forensic inspection

        System.out.println(
            "Auditing ledger event: " + event.transactionId()
        );
    }
}