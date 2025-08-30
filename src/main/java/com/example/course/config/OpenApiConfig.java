package com.example.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI() {


        Contact contact = new Contact();
        contact.setEmail("walmiralves.dev@gmail.com");
        contact.setName("Walmir Alves");

        Info info = new Info()
                .title("My API")
                .version("1.0.0")
                .description("API documentation of my project")
                .contact(contact);

        return new OpenAPI().info(info);
    }

}
