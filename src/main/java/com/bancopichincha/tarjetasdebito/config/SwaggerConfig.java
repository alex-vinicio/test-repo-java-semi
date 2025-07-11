package com.bancopichincha.tarjetasdebito.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Gestión de Tarjetas de Débito - Banco Pichincha")
                        .version("1.0.0")
                        .description("API REST para la gestión de tarjetas de débito del Banco Pichincha. " +
                                "Permite crear, consultar, actualizar y administrar tarjetas de débito de los clientes.")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo Banco Pichincha")
                                .email("desarrollo@bancopichincha.com")
                                .url("https://www.bancopichincha.com"))
                        .license(new License()
                                .name("Banco Pichincha License")
                                .url("https://www.bancopichincha.com/license")));
    }
}
