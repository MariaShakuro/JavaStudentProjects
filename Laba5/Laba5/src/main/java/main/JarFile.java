package main;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
public class JarFile {
    public static void jarFile(String sourceFilePath, String jarFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(jarFilePath);
             JarOutputStream jos = new JarOutputStream(fos);
             FileInputStream fis = new FileInputStream(sourceFilePath)) {
            JarEntry jarEntry = new JarEntry(new File(sourceFilePath).getName());
            jos.putNextEntry(jarEntry);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                jos.write(buffer, 0, length);
            }
            jos.closeEntry();
            System.out.println("JAR файл успешно создан: " + jarFilePath);
        }
    }
}

