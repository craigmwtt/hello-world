# Hello Camel

A command-line Java application that sends dynamic string input through an Apache Camel route.

Requirements: JDK 25 or newer and Maven.

## Run with a command-line argument

```sh
mvn compile exec:java -Dexec.args="hello Camel"
```

## Run with interactive standard input

```sh
mvn compile exec:java
```

Enter text at the prompt and press Enter. Empty input uses `Hello World`.
