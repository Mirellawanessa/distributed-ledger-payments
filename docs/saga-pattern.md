# Saga Pattern for Financial Transactions

## 1. Motivation

Financial transactions **must not rely on database rollbacks**.
Once a financial fact is recorded, it must remain observable.

This platform uses the **Saga Pattern** to coordinate multi-step financial operations
through **compensating actions**, not rollbacks.

---

## 2. What Is a Saga

A Saga is a sequence of local transactions where:
- Each step publishes an event
- Failures trigger compensating actions
- The system eventually reaches a consistent state

---

## 3. Why Rollbacks Are Forbidden

Database rollbacks:
- Hide historical intent
- Break auditability
- Violate accounting principles

In financial systems:
> **Errors are corrected, not erased.**

---

## 4. Saga Flow Example

### Payment Flow

1. Payment intent received
2. `PaymentPostedEvent` published
3. Ledger records DEBIT and CREDIT
4. Transaction completes successfully

### Failure Scenario

1. Debit recorded
2. Credit fails
3. `PaymentFailedEvent` published
4. Compensation event emitted
5. New ledger entries neutralize the partial effect

No data is removed. State evolves transparently.

---

## 5. Compensation Model

Compensation is implemented as:

- New financial events
- New ledger entries
- Opposite amounts to the original operation

Example:

| Original | Compensation |
|--------|--------------|
| DEBIT -100 | CREDIT +100 |
| CREDIT +100 | DEBIT -100 |

---

## 6. Benefits

- Full audit trail
- Event-driven recovery
- Resilience to partial failures
- Alignment with financial regulations

---

## 7. Design Philosophy

Sagas embrace **eventual consistency of workflows**
while preserving **strong consistency of financial data**.

This separation is critical in distributed financial systems.
