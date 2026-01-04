package com.fintech.reconciliation;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LedgerEventConsumer {

    @KafkaListener(
        topics = "ledger-events",
        groupId = "reconciliation"
    )
    public void reconcile(LedgerEntryCreatedEvent event) {

        // 1. Verify debit and credit entries
        // 2. Total sum must equal ZERO (double-entry invariant)
        // 3. If the rule is violated → trigger alert (Slack, log, metric)

        System.out.println(
            "Reconciling ledger event: " + event.transactionId()
        );
    }
}