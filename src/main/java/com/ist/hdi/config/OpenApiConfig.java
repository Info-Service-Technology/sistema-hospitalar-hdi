package com.ist.hdi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Sistema Médico Hospitalar")
                .description("API REST de controle de Hospital.")
                .version("1.0")
                .contact(new Contact()
                    .name("Mauro Lúcio")
                    .url("https://infoservicetechnology.com")
                    .email("mauroslucios@gmail.com"))
                .license(new License()
                    .name("Apache License Version 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0"))
            );
    }
}
