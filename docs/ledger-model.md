# Immutable Ledger Model (Double-Entry Accounting)

## 1. Purpose

This document defines the immutable ledger model used by the platform.
The goal is to ensure **financial correctness, auditability, and regulatory compliance**
through a strict **double-entry accounting system**.

The ledger is the **authoritative financial record** of the platform.

---

## 2. Core Principles

### 2.1 Immutability

Ledger entries are **append-only**:
- No UPDATE
- No DELETE
- No in-place correction

Any correction or reversal is applied through **compensating entries**.

This guarantees:
- Full historical traceability
- Deterministic state reconstruction
- Compliance with financial auditing standards

---

### 2.2 Double-Entry Accounting

Every financial transaction generates **exactly two ledger entries**:

| Entry | Account | Amount |
|-----|--------|--------|
| DEBIT | Source account | -X |
| CREDIT | Destination account | +X |

The invariant:
Σ(debits) + Σ(credits) = 0


This invariant is enforced at the application level and validated during reconciliation.

---

## 3. Ledger Entry Structure

Each ledger entry contains:

- `ledger_entry_id` (UUID)
- `transaction_id` (UUID)
- `account_id` (UUID)
- `amount` (numeric, signed)
- `entry_type` (DEBIT or CREDIT)
- `created_at` (timestamp)

Ledger entries are **facts**, not mutable state.

---

## 4. Balance Calculation

Account balances are **derived**, never stored.
balance(account) = SUM(amount WHERE account_id = X)


Benefits:
- Eliminates balance drift
- Guarantees consistency
- Enables deterministic recovery via event replay

---

## 5. Idempotency

Each transaction is processed exactly once using:
- Unique transaction identifiers
- Idempotency checks at the ledger boundary

Duplicate events **must not** generate duplicate ledger entries.

---

## 6. Event Sourcing Integration

The ledger is populated exclusively by consuming **financial events**.
The database acts as a **materialized view** of the event stream.

This allows:
- Full event replay
- Time-travel debugging
- Recovery from catastrophic failures

---

## 7. Why This Model

This ledger model aligns with:
- Core banking systems
- Payment processors
- Regulated financial institutions

It prioritizes **correctness over convenience**, which is mandatory in financial systems.



