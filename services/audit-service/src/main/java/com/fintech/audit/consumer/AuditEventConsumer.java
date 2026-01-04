package com.fintech.audit.consumer;

import com.fintech.audit.service.AuditService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AuditEventConsumer {

    private final AuditService auditService;

    public AuditEventConsumer(AuditService auditService) {
        this.auditService = auditService;
    }

    @KafkaListener(topics = "ledger-events", groupId = "audit")
    public void audit(Object event) {
        auditService.record(event);
    }
}