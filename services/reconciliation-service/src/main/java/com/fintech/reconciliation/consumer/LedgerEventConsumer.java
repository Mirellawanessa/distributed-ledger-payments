package com.fintech.reconciliation.consumer;

import com.fintech.reconciliation.model.LedgerEntryView;
import com.fintech.reconciliation.service.ReconciliationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LedgerEventConsumer {

    private final ReconciliationService reconciliationService;

    public LedgerEventConsumer(ReconciliationService reconciliationService) {
        this.reconciliationService = reconciliationService;
    }

    @KafkaListener(
        topics = "ledger-events",
        groupId = "reconciliation"
    )
    public void consume(LedgerEntryView event) {
        reconciliationService.reconcile(event);
    }
}
