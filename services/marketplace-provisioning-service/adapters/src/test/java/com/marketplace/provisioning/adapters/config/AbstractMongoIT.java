package com.marketplace.provisioning.adapters.config;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractMongoIT {

    static final MongoDBContainer mongo;

    static {
        mongo = new MongoDBContainer("mongo:7.0")
                .withReuse(true);
        mongo.start();
        System.out.println("Mongo URI: " + mongo.getReplicaSetUrl());
    }

    @DynamicPropertySource
    static void registerMongoProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.data.mongodb.uri", mongo::getReplicaSetUrl);

        // Liquibase Mongo používa rovnaké pripojenie
        registry.add("spring.liquibase.url", mongo::getReplicaSetUrl);
    }
}

