package com.agenciaviagens.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI agenciaViagensOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Agência de Viagens")
                        .description("API para gestão de uma agência de viagens")
                        .version("v1.0"));
    }
}
