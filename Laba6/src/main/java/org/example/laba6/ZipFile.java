package org.example.laba6;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class ZipFile {
    public static void zipFile(String sourceFilePath, String zipFilePath)throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(sourceFilePath)) {
            ZipEntry zipEntry = new ZipEntry(new File(sourceFilePath).getName());
            zos.putNextEntry(zipEntry);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            System.out.println("ZIP файл успешно создан: " + zipFilePath);
        }
    }
}


