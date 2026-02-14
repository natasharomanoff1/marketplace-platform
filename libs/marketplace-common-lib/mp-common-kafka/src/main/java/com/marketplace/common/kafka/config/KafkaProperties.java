package com.marketplace.common.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "marketplace.kafka")
public class KafkaProperties {

    /**
     * Kafka bootstrap servers
     */
    private String bootstrapServers;

    /**
     * Consumer configuration
     */
    private Consumer consumer = new Consumer();

    /**
     * Producer configuration
     */
    private Producer producer = new Producer();

    /**
     * Topics configuration
     */
    private Topic topic = new Topic();

    /**
     * Batch processing settings
     */
    private Batch batch = new Batch();

    /**
     * Listener settings
     */
    private Listener listener = new Listener();

    /**
     * Retry / backoff settings
     */
    private Retry retry = new Retry();

    /**
     * Serialization settings
     */
    private Serialization serialization = new Serialization();

    // ---------------- CONSUMER ----------------

    @Data
    public static class Consumer {

        private String groupId;
        private String autoOffsetReset = "earliest";

        /**
         * Enable auto commit
         */
        private Boolean enableAutoCommit = false;
    }

    // ---------------- PRODUCER ----------------

    @Data
    public static class Producer {

        private Integer retries = 3;
        private String acks = "all";
    }

    // ---------------- BATCH ----------------

    @Data
    public static class Batch {

        private Integer maxPollRecords = 500;
        private Integer fetchMinBytes = 1_000_000;
        private Integer fetchMaxWaitMs = 500;
    }

    // ---------------- TOPICS ----------------

    @Data
    public static class Topic {

        private String dlq;
    }

    // ---------------- LISTENER ----------------

    @Data
    public static class Listener {

        /**
         * Kafka listener concurrency
         */
        private Integer concurrency = 1;

        /**
         * Enable batch listener
         */
        private boolean batch = false;
    }

    // ---------------- RETRY ----------------

    @Data
    public static class Retry {

        private Long initialIntervalMs = 1000L;
        private Double multiplier = 2.0;
        private Long maxIntervalMs = 30000L;
        private Integer maxAttempts = 3;
    }

    // ---------------- SERIALIZATION ----------------

    @Data
    public static class Serialization {

        /**
         * Trusted packages for JsonDeserializer
         */
        private String trustedPackages = "*";
    }
}
