package com.marketplace.common.kafka.exception;


public class KafkaProcessingException extends RuntimeException {

    public KafkaProcessingException() {
        super();
    }

    public KafkaProcessingException(String message) {
        super(message);
    }

    public KafkaProcessingException(Throwable cause) {
        super(cause);
    }

    public KafkaProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
