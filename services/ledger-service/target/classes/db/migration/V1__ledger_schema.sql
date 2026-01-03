CREATE TABLE accounts (
    id UUID PRIMARY KEY,
    account_type VARCHAR(50) NOT NULL,
    currency CHAR(3) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    external_reference VARCHAR(100),
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE ledger_entries (
    id UUID PRIMARY KEY,
    transaction_id UUID NOT NULL,
    account_id UUID NOT NULL,
    amount NUMERIC(18,2) NOT NULL,
    entry_type VARCHAR(6) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT chk_entry_type CHECK (entry_type IN ('DEBIT', 'CREDIT')),
    CONSTRAINT chk_amount CHECK (amount > 0),

    FOREIGN KEY (transaction_id) REFERENCES transactions(id),
    FOREIGN KEY (account_id) REFERENCES accounts(id)
);

REVOKE UPDATE, DELETE ON ledger_entries FROM PUBLIC;
