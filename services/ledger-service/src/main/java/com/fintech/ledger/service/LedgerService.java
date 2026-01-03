package com.fintech.ledger.service;

import com.fintech.ledger.domain.Account;
import com.fintech.ledger.domain.LedgerEntry;
import com.fintech.ledger.domain.Transaction;
import com.fintech.ledger.domain.LedgerEntry.EntryType;
import com.fintech.ledger.repository.AccountRepository;
import com.fintech.ledger.repository.LedgerEntryRepository;
import com.fintech.ledger.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class LedgerService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final LedgerEntryRepository ledgerEntryRepository;

    public LedgerService(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository,
            LedgerEntryRepository ledgerEntryRepository
    ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.ledgerEntryRepository = ledgerEntryRepository;
    }

    /**
     * Posts a double-entry financial transaction.
     * One DEBIT and one CREDIT entry are always generated.
     */
    @Transactional
    public UUID post(
            UUID fromAccountId,
            UUID toAccountId,
            BigDecimal amount,
            String externalReference
    ) {
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("From account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("To account not found"));

        // Create transaction context
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                externalReference,
                "POSTED"
        );
        transactionRepository.save(transaction);

        // Debit entry
        LedgerEntry debit = new LedgerEntry(
                UUID.randomUUID(),
                transaction,
                fromAccount,
                amount,
                EntryType.DEBIT
        );

        // Credit entry
        LedgerEntry credit = new LedgerEntry(
                UUID.randomUUID(),
                transaction,
                toAccount,
                amount,
                EntryType.CREDIT
        );

        ledgerEntryRepository.save(debit);
        ledgerEntryRepository.save(credit);

        return transaction.getId();
    }

    /**
     * Derives account balance from immutable ledger entries.
     */
    @Transactional(readOnly = true)
    public BigDecimal getBalance(UUID accountId) {
        return ledgerEntryRepository.calculateBalance(accountId);
    }
}
