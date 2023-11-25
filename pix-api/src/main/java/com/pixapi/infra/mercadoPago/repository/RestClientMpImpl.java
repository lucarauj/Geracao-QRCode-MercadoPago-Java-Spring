package com.pixapi.infra.mercadoPago.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixapi.infra.mercadoPago.dto.MpPixDto;
import com.pixapi.infra.mercadoPago.dto.MpPixResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class RestClientMpImpl implements RestClientMp {

    private final URI baseUri;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public RestClientMpImpl(URI baseUri, HttpClient httpClient, ObjectMapper objectMapper) {
        this.baseUri = baseUri;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

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

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                MpPixResponse mpPixResponse = objectMapper.readValue(response.body(), MpPixResponse.class);
                return mpPixResponse;
            } else {
                System.out.println("Erro na solicitação: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
