package com.afriasdev.donacionsangrerd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Donación de Sangre API")
                        .version("1.0.0")
                        .description("Documentación de la API REST para el sistema de donación de sangre en RD")
                        .contact(new Contact()
                                .name("Andrés Frías")
                                .email("afriasdev@gmail.com")
                                .url("https://github.com/SentenciaSQL")
                        )
                );
    }

}
