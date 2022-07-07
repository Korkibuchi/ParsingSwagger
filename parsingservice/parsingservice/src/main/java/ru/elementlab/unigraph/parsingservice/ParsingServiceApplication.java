package ru.elementlab.unigraph.parsingservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
class ParsingServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ParsingServiceApplication.class).run(args);
    }
}
