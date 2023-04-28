package com.slabko.parser;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Recommendation service",
        version = "1.0",
        description = "Crypto recommendation service"
    )
)
public class CsvParserAPI {

    public static void main(String[] args) {
        SpringApplication.run(CsvParserAPI.class, args);
    }

}
