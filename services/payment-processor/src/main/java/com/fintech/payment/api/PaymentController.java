@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentProcessorService service;

    public PaymentController(PaymentProcessorService service) {
        this.service = service;
    }

    @PostMapping
    public void pay(@RequestBody PaymentInitiatedEvent event) {
        service.process(event);
    }
}
