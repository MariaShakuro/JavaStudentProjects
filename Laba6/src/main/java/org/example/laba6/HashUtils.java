package org.example.laba6;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtils {
    public static String hashData(String data) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        String hashedData = Base64.getEncoder().encodeToString(hash);

        // записать хэш в файл
        File file = new File("hashedData.txt");
        FileWriter writer = new FileWriter(file);
        writer.write(hashedData);
        writer.close();

        return hashedData;
    }
}

