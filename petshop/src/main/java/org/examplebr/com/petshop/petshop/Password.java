package org.examplebr.com.petshop.petshop;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Password {

    // A chave deve ter 16 bytes para AES
    private static final String SECRET_KEY = "1234567890123456";

    public static String encrypt(String password) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedPassword) throws Exception {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);

            // Certifique-se de que a string está codificada corretamente
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (BadPaddingException e) {
            throw new IllegalArgumentException("Erro de padding: dados corrompidos ou chave incorreta", e);
        } catch (Exception e) {
            throw new Exception("Erro na descriptografia: " + e.getMessage(), e);
        }
    }
}
