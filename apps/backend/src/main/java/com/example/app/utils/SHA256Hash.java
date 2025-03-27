
package com.example.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class SHA256Hash {
    public static String hash(String input) {
        try {
            // Create a SHA-256 MessageDigest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Perform the hash computation
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert byte array into a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b)); // Convert byte to hex
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found!", e);
        }
    }

    public static String signWithSecretKey(String input, String secretKey) {
        return hash(input + secretKey);
    }

    public static String generateSalt() {
        return hash(String.valueOf(System.currentTimeMillis()));
    }

    public static void main(String[] args) {
        String text = "Hello, World!";
        String hashedText = hash(text);
        System.out.println("Original: " + text);
        System.out.println("SHA-256: " + hashedText);
    }
}