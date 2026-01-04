@Service
public class PaymentProcessorService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentProcessorService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void process(PaymentInitiatedEvent event) {

        kafkaTemplate.executeInTransaction(kt -> {

            kt.send("payment-intents", event.transactionId().toString(), event);

            // Here, in the future:
            // - Call to Ledger Service
            // - Idempotent persistence
            // - Publish PaymentPosted / PaymentFailed events

            kt.send(
                "payment-posted",
                event.transactionId().toString(),
                new PaymentPostedEvent(event.transactionId())
            );

            return true;
        });
    }
}