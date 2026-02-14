package com.marketplace.provisioning.application.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccessTokenServiceTest {

    private final AccessTokenService service = new AccessTokenService();

    @Test
    void generatePlainToken_shouldGenerateTokenWithPrefix() {

        String token = service.generatePlainToken();

        assertThat(token).isNotNull();
        assertThat(token).startsWith("mkt_");
        assertThat(token.length()).isGreaterThan(10);
    }

    @Test
    void hash_shouldReturnSameHashForSameToken() {

        String token = "test-token";

        String hash1 = service.hash(token);
        String hash2 = service.hash(token);

        assertThat(hash1).isEqualTo(hash2);
    }

    @Test
    void hash_shouldReturnDifferentHashesForDifferentTokens() {

        String hash1 = service.hash("token-1");
        String hash2 = service.hash("token-2");

        assertThat(hash1).isNotEqualTo(hash2);
    }
}
