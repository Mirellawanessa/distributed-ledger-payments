package com.fintech.reconciliation.service;

import com.fintech.reconciliation.model.LedgerEntryView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReconciliationService {

    private static final Logger log =
            LoggerFactory.getLogger(ReconciliationService.class);

    public void reconcile(LedgerEntryView entry) {

        log.info(
            "Reconciling transaction={} account={} type={} amount={}",
            entry.transactionId(),
            entry.accountId(),
            entry.type(),
            entry.amount()
        );

        validateEntry(entry);

        // Natural next steps (Staff/Principal Engineer level):
        // - Group entries by transactionId
        // - Enforce exactly one DEBIT and one CREDIT per transaction
        // - Guarantee net sum equals zero (double-entry invariant)
        // - Emit metrics or alerts on detected inconsistencies
    }

    private void validateEntry(LedgerEntryView entry) {
        if (!"DEBIT".equals(entry.type()) && !"CREDIT".equals(entry.type())) {
            throw new IllegalArgumentException(
                "Invalid ledger entry type: " + entry.type()
            );
        }
    }
}