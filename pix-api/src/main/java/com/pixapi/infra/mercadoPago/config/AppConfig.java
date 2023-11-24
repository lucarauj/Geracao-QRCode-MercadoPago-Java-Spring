package com.pixapi.infra.mercadoPago.config;

import java.net.URI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public URI mercadoPagoBaseUri() {
        return URI.create("https://api.mercadopago.com");
    }
}
