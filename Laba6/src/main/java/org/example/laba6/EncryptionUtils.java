package org.example.laba6;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtils {
    private static final String SECRET_KEY = "your_secret_key_"; // Замените ключ на надежный(должен быть 16/24/32)

    public static String encrypt(String data) throws Exception {
        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        String encryptedData = java.util.Base64.getEncoder().encodeToString(encryptedBytes);
        // записать зашифрованные данные в файл
        File file = new File("encryptedData.txt");
        FileWriter writer = new FileWriter(file);
        writer.write(encryptedData);
        writer.close();

        return encryptedData;
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        String decryptedData = new String(decryptedBytes, StandardCharsets.UTF_8);
        // записать расшифрованные данные в файл
        File file = new File("decryptedData.txt");
        FileWriter writer = new FileWriter(file);
        writer.write(decryptedData);
        writer.close();

        return decryptedData;
    }
}


