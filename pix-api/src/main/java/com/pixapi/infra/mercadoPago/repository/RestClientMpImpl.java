package com.pixapi.infra.mercadoPago.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixapi.infra.mercadoPago.dto.MpPixDto;
import com.pixapi.infra.mercadoPago.dto.MpPixResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class RestClientMpImpl implements RestClientMp {

    private final URI baseUri;

    @Autowired
    public RestClientMpImpl(URI baseUri) {
        this.baseUri = baseUri;
    }

    private static final Logger logger = LoggerFactory.getLogger(RestClientMpImpl.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MpPixResponse createPix(String token, String idempotency, MpPixDto payload) {
        String url = baseUri + "/v1/payments";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .header("X-Idempotency-Key", idempotency)
                .POST(HttpRequest.BodyPublishers.ofString(payload.toJson()))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                MpPixResponse mpPixResponse = objectMapper.readValue(response.body(), MpPixResponse.class);
                return mpPixResponse;
            } else {
                logger.warn("Requisição retornou código de status não esperado: {}", response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            logger.error("Erro ao fazer a requisição HTTP", e);
        }

        return null;
    }
}
