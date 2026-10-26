package com.example.helloworld;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class HelloCamelApplicationTest {

    @Test
    void routesJoinedCommandLineArguments() throws Exception {
        assertEquals("hello Camel" + System.lineSeparator(), runApplication(
                new ByteArrayInputStream(new byte[0]), "hello", "Camel"));
    }

    @Test
    void usesDefaultForBlankInteractiveInput() throws Exception {
        assertEquals("Enter a string: Hello World" + System.lineSeparator(), runApplication(
                new ByteArrayInputStream(System.lineSeparator().getBytes(StandardCharsets.UTF_8))));
    }

    private static String runApplication(InputStream input, String... args) throws Exception {
        InputStream originalInput = System.in;
        PrintStream originalOutput = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (PrintStream capturedOutput = new PrintStream(output, true, StandardCharsets.UTF_8)) {
            System.setIn(input);
            System.setOut(capturedOutput);
            HelloCamelApplication.main(args);
        } finally {
            System.setIn(originalInput);
            System.setOut(originalOutput);
        }
        return output.toString(StandardCharsets.UTF_8);
    }
}
