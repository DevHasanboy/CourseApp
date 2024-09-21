package com.example.file.courseapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        tags = {
                @Tag(name = "CREATE"),
                @Tag(name = "GET"),
                @Tag(name = "UPDATE"),
                @Tag(name = "DELETE"),
                @Tag(name = "GET-ALL"),
                @Tag(name = "GET-ALL-PAGE")
        }
)
@SpringBootApplication
public class CourseAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseAppApplication.class, args);
    }

}
