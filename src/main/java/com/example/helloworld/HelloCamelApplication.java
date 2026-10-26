package com.example.helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public final class HelloCamelApplication {
    public static void main(String[] args) throws Exception {
        String input = args.length > 0
                ? String.join(" ", args)
                : readFromStandardInput();

        try (CamelContext context = new DefaultCamelContext()) {
            context.addRoutes(new org.apache.camel.builder.RouteBuilder() {
                @Override
                public void configure() {
                    from("direct:hello")
                            .process(exchange -> exchange.getIn().setBody(
                                    exchange.getIn().getBody(String.class)));
                }
            });

            context.start();
            String result = context.createProducerTemplate()
                    .requestBody("direct:hello", input, String.class);
            System.out.println(result);
        }
    }

    private static String readFromStandardInput() throws IOException {
        System.out.print("Enter a string: ");
        System.out.flush();
        String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        return input == null || input.isBlank() ? "Hello World" : input;
    }
}
