package com.fintech.reconciliation.service;

import com.fintech.reconciliation.model.LedgerEntryView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReconciliationService {

    private static final Logger log = LoggerFactory.getLogger(ReconciliationService.class);

    public void reconcile(LedgerEntryView entry) {

        // Core reconciliation rule:
        // Every transaction must have exactly one DEBIT and one CREDIT entry.
        log.info(
                "Reconciling transaction={} account={} type={} amount={}",
                entry.transactionId(),
                entry.accountId(),
                entry.type(),
                entry.amount()
        );

        // Future enhancements:
        // - Validate that total sum per transaction equals zero
        // - Detect discrepancies or orphaned entries
        // - Trigger alerts (metrics, logs, notifications)
    }
}