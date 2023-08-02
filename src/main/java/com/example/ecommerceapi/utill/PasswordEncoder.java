package com.example.ecommerceapi.utill;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 23-Jul-23
 */
@Component
public class PasswordEncoder {
    public String hashPassword(String password) {
        String hashedPassword = null;
        try {
            // Use PBKDF2 with 65536 iterations and 256-bit key length
            int iterations = 65536;
            int keyLength = 256;
            char[] passwordChars = password.toCharArray();
            byte[] salt = new byte[64]; // Generate a new random salt for each password
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(passwordChars, salt, iterations, keyLength);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            hashedPassword = Base64.getEncoder().encodeToString(hash);
//            System.out.printf("salt: %s%n", Base64.getEncoder().encodeToString(salt));
//            System.out.printf("hash: %s%n", Base64.getEncoder().encodeToString(hash));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException("Error hashing the password.");
        }
        return hashedPassword;
    }
}
