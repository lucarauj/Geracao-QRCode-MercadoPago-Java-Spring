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
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public RestClientMpImpl(URI baseUri, HttpClient httpClient, ObjectMapper objectMapper) {
        this.baseUri = baseUri;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    private static final Logger logger = LoggerFactory.getLogger(RestClientMpImpl.class);

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

            logRequestAndResponse(url, request, response);

            if (response.statusCode() == 200) {
                MpPixResponse mpPixResponse = objectMapper.readValue(response.body(), MpPixResponse.class);
                if (mpPixResponse != null) {
                    return mpPixResponse;
                } else {
                    logger.warn("Response body is empty");
                }
            } else {
                handleErrorResponse(response);
            }
        } catch (IOException | InterruptedException e) {
            handleRequestException(e);
        }

        return null;
    }

    private void logRequestAndResponse(String url, HttpRequest request, HttpResponse<String> response) {
        logger.info("HTTP Request to {}: {}", url, request.method());
        logger.info("Headers: {}", request.headers().map());
        logger.info("Request Body: {}", request.bodyPublisher().orElse(HttpRequest.BodyPublishers.noBody()));

        logger.info("HTTP Response: {}", response.statusCode());
        logger.info("Response Headers: {}", response.headers().map());
        logger.info("Response Body: {}", response.body());
    }

    private void handleErrorResponse(HttpResponse<String> response) {
        logger.warn("HTTP Request returned unexpected status code: {}", response.statusCode());
        logger.warn("Response Body: {}", response.body());
    }

    private void handleRequestException(Exception e) {
        logger.error("Error making HTTP request", e);
    }
}
