package com.fintech.audit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    private static final Logger log = LoggerFactory.getLogger(AuditService.class);

    public void record(Object event) {
        log.info("AUDIT EVENT RECEIVED: {}", event);
        // Future enhancements:
        // - Immutable persistence (e.g., write-once storage)
        // - Cryptographic hashing for tamper evidence
        // - Regulatory audit trail compliance (e.g., PCI-DSS, SOX)
    }
}