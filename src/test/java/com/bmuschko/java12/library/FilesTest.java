package com.bmuschko.java12.library;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilesTest {
    @Test
    void canMatchFiles(@TempDir Path tempDir) throws IOException {
        Path helloWorld = tempDir.resolve("helloworld.txt");
        createFile(helloWorld, "Hello World!");
        Path java12 = tempDir.resolve("java12.txt");
        createFile(java12, "Released on March 19th, 2019");
        Path helloJava = tempDir.resolve("hellojava.txt");
        createFile(helloJava, "Hello Java!");
        assertEquals(-1, Files.mismatch(helloWorld, helloWorld));
        assertEquals(0, Files.mismatch(helloWorld, java12));
        assertEquals(6, Files.mismatch(helloWorld, helloJava));
    }

    private static void createFile(Path path, String content) {
        try {
            Files.write(path, content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write content to file", e);
        }
    }
}
