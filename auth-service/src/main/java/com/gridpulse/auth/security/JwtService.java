package com.gridpulse.auth.security;

import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class JwtService {

    private static final String SECRET =
            "GridPulseSmartGridSecretKeyForJwt2026VeryLong";

    // Generate JWT token
    public String generateToken(String username, String role) {

        String header = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";

        long issuedAt = System.currentTimeMillis();
        long expiration = issuedAt + (60 * 60 * 1000);

        String payload = String.format(
                "{\"sub\":\"%s\",\"role\":\"%s\",\"iat\":%d,\"exp\":%d}",
                username,
                role,
                issuedAt / 1000,
                expiration / 1000
        );

        String encodedHeader = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(
                        header.getBytes(StandardCharsets.UTF_8)
                );

        String encodedPayload = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(
                        payload.getBytes(StandardCharsets.UTF_8)
                );

        String data = encodedHeader + "." + encodedPayload;

        String signature = sign(data);

        return data + "." + signature;
    }

    // Extract username from JWT
    public String extractUsername(String token) {

        try {

            String[] parts = token.split("\\.");

            if (parts.length != 3) {
                throw new RuntimeException("Invalid JWT token");
            }

            String payload = new String(
                    Base64.getUrlDecoder().decode(parts[1]),
                    StandardCharsets.UTF_8
            );

            String search = "\"sub\":\"";

            int start = payload.indexOf(search);

            if (start == -1) {
                throw new RuntimeException("Username not found in token");
            }

            start = start + search.length();

            int end = payload.indexOf("\"", start);

            return payload.substring(start, end);

        } catch (Exception e) {

            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    // Create JWT signature
    private String sign(String data) {

        try {

            SecretKeySpec key = new SecretKeySpec(
                    SECRET.getBytes(StandardCharsets.UTF_8),
                    "HmacSHA256"
            );

            Mac mac = Mac.getInstance("HmacSHA256");

            mac.init(key);

            byte[] signature = mac.doFinal(
                    data.getBytes(StandardCharsets.UTF_8)
            );

            return Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(signature);

        } catch (Exception e) {

            throw new RuntimeException(
                    "JWT generation failed",
                    e
            );
        }
    }
}