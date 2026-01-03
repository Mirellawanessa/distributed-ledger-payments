package com.fintech.ledger.api;

import com.fintech.ledger.service.LedgerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/ledger")
public class LedgerController {

    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public TransferResponse transfer(@RequestBody TransferRequest request) {

        UUID transactionId = ledgerService.post(
                request.fromAccountId(),
                request.toAccountId(),
                request.amount(),
                request.externalReference()
        );

        return new TransferResponse(transactionId);
    }

    @GetMapping("/balance/{accountId}")
    public BalanceResponse balance(@PathVariable UUID accountId) {

        BigDecimal balance = ledgerService.getBalance(accountId);
        return new BalanceResponse(accountId, balance);
    }

    // ===== DTOs =====

    public record TransferRequest(
            UUID fromAccountId,
            UUID toAccountId,
            BigDecimal amount,
            String externalReference
    ) {}

    public record TransferResponse(UUID transactionId) {}

    public record BalanceResponse(UUID accountId, BigDecimal balance) {}
}
