package com.marketplace.provisioning.application.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AccessTokenService {

    private static final SecureRandom RANDOM = new SecureRandom();

    public String generatePlainToken() {
        byte[] bytes = new byte[32];
        RANDOM.nextBytes(bytes);
        String randomPart = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);

        return "mkt_" + randomPart;
    }

    public String hash(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
