public record PaymentInitiatedEvent(
        UUID transactionId,
        UUID fromAccount,
        UUID toAccount,
        BigDecimal amount
) {}
