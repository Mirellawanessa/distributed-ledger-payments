package com.fintech.ledger.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    private UUID id;

    @Column(name = "external_reference")
    private String externalReference;

    @Column(nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    // ===== Construtores =====

    protected Transaction() {
        // JPA only
    }

    public Transaction(UUID id, String externalReference, String status) {
        this.id = id;
        this.externalReference = externalReference;
        this.status = status;
    }

    // ===== Getters =====

    public UUID getId() {
        return id;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
