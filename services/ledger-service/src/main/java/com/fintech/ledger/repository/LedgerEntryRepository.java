package com.fintech.ledger.repository;

import com.fintech.ledger.domain.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.UUID;

public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, UUID> {

    @Query("""
        SELECT COALESCE(SUM(
            CASE
                WHEN e.entryType = 'CREDIT' THEN e.amount
                ELSE -e.amount
            END
        ), 0)
        FROM LedgerEntry e
        WHERE e.account.id = :accountId
    """)
    BigDecimal calculateBalance(UUID accountId);
}
