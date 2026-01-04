# Distributed Ledger Payments Platform

A fintech-grade, event-driven payments platform designed to demonstrate
**strong consistency**, **financial correctness**, **auditability**, and **horizontal scalability**.

This project models architectural patterns used in modern **core banking systems**
and high-scale payment platforms such.

---

## 🎯 Goals

- Demonstrate real-world financial system design
- Implement an immutable double-entry ledger
- Ensure exactly-once financial event processing
- Provide full audit and reconciliation capabilities
- Support high throughput with deterministic recovery

---

## 🧠 Architectural Overview

The platform is built around **event sourcing** and an **immutable ledger**.

Client
↓
Payment Processor
↓
Kafka (event backbone)
↓
Ledger Service
↓
PostgreSQL (immutable ledger)
↓
Audit & Reconciliation


### Key Principles

- **Events as the source of truth**
- **Append-only double-entry accounting**
- **Saga-based compensation (no rollbacks)**
- **Independent audit and reconciliation**
- **Strong consistency for financial state**

---

## 🧱 Services

### Payment Processor
- Receives payment intents
- Validates business rules
- Publishes financial events
- Does not mutate financial state

### Ledger Service
- Consumes payment events
- Enforces double-entry accounting
- Persists immutable ledger entries
- Guarantees idempotency

### Reconciliation Service
- Verifies debit/credit symmetry
- Detects inconsistencies
- Runs independently from core flow

### Audit Service
- Records full financial trail
- Supports compliance and forensics
- Read-only by design

---

## 🛠️ Technology Stack

- Java 21 / Spring Boot 3
- Apache Kafka (event backbone)
- PostgreSQL (immutable ledger)
- Docker & Docker Compose
- k6 (load testing)
- Prometheus & Grafana (planned)
- AWS-compatible architecture (MSK, RDS, IAM)

---

## ⚖️ Financial Correctness

- Every transaction produces:
  - 1 DEBIT entry
  - 1 CREDIT entry
- Ledger entries are **never updated or deleted**
- Corrections are applied via compensating events
- Balances are derived, not stored

---

## 🔁 Failure Handling

- No partial commits
- No database rollbacks
- Deterministic recovery via event replay
- Consumer isolation with `read_committed`

---

## 📈 Load Testing

The project includes k6 tests simulating concurrent payment traffic:

- Measures p95 latency
- Tracks error rate
- Supports TPS and cost estimation

Run example:

```bash
k6 run k6/load-test.js

## 🚀 Why This Project Matters

This repository is **not** a demo CRUD app.

It demonstrates:

- Real financial domain modeling  
- Production-grade consistency guarantees  
- Clear separation of concerns  
- Design decisions aligned with regulated environments

## 📌 Next Steps (Planned)

- Avro schemas + Schema Registry  
- Exactly-once semantics end-to-end  
- Chaos engineering scenarios  
- Cost per TPS estimation on AWS

## PM
🤝 Contribution
Contributions are welcome! Feel free to open issues and pull requests.

[![LinkedIn](https://img.shields.io/badge/in/mirellawanessa-fff?style=flat&logo=linkedin&logoColor=FFFFFF&labelColor=8b7bdf)](https://www.linkedin.com/in/mirellawanessa)  
[![Instagram](https://img.shields.io/badge/@myfilearchive-fff?style=flat&logo=instagram&logoColor=FFFFFF&labelColor=8b7bdf)](https://www.instagram.com/myfilearchive)
