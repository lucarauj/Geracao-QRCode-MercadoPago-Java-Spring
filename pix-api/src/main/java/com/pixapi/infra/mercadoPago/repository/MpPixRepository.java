package com.pixapi.infra.mercadoPago.repository;

import com.pixapi.domain.dto.PixDto;
import com.pixapi.domain.repositories.IPixRepository;
import com.pixapi.infra.mercadoPago.dto.MpPixDto;
import com.pixapi.infra.mercadoPago.dto.MpPixPayerDto;
import com.pixapi.infra.mercadoPago.dto.MpPixResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class MpPixRepository implements IPixRepository {

    @Autowired
    private RestClientMp restClient;
    @Override
    public PixDto create(PixDto dto) {
        String token = "Bearer TEST-4025927553539549-112316-24a3bc07d102c9454bf12959d92b153e-184173903";
        String idempotencyKey = UUID.randomUUID().toString();

        MpPixPayerDto payerDto = new MpPixPayerDto();
        MpPixDto pixDto = new MpPixDto();
        payerDto.setEmail(dto.getEmail());

        pixDto.setAmount(dto.getAmount());
        pixDto.setDescription(dto.getDescription());
        pixDto.setPaymentMethodId("pix");
        pixDto.setPayer(payerDto);

        MpPixResponse response = restClient.createPix(token, idempotencyKey, pixDto);

        dto.setEmv(response.getPoi().getTrxData().getEmv());
        dto.setBase64(response.getPoi().getTrxData().getBase64());

        return dto;
    }
}
