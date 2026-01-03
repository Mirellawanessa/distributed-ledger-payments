package com.fintech.ledger.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "ledger_entries")
public class LedgerEntry {

    @Id
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "entry_type", nullable = false)
    private EntryType entryType;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    // ===== Construtores =====

    protected LedgerEntry() {
        // JPA only
    }

    public LedgerEntry(
            UUID id,
            Transaction transaction,
            Account account,
            BigDecimal amount,
            EntryType entryType
    ) {
        this.id = id;
        this.transaction = transaction;
        this.account = account;
        this.amount = amount;
        this.entryType = entryType;
    }

    // ===== Getters =====

    public UUID getId() {
        return id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Account getAccount() {
        return account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    // ===== Enum interno =====

    public enum EntryType {
        DEBIT,
        CREDIT
    }
}
