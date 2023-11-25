package com.pixapi.infra.mercadoPago.repository;

import com.pixapi.infra.mercadoPago.dto.MpPixDto;
import com.pixapi.infra.mercadoPago.dto.MpPixResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface RestClientMp {

    @POST
    @Path("/v1/payments")
    @Consumes(MediaType.APPLICATION_JSON)
    MpPixResponse createPix(
            @HeaderParam("Authorization") String token,
            @HeaderParam("X-Idempotency-Key") String idempotency,
            MpPixDto payload);
}

