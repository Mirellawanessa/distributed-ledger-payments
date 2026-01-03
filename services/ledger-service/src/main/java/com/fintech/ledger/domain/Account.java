package com.fintech.ledger.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private UUID id;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    // ===== Construtores =====

    protected Account() {
        // JPA only
    }

    public Account(UUID id, String accountType, String currency) {
        this.id = id;
        this.accountType = accountType;
        this.currency = currency;
    }

    // ===== Getters =====

    public UUID getId() {
        return id;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
