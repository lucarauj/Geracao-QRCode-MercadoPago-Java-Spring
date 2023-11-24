package com.pixapi.service;

import com.pixapi.domain.dto.PixDto;
import com.pixapi.domain.usecases.CreatePix;
import com.pixapi.infra.mercadoPago.repository.MpPixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PixService {

    @Autowired
    private MpPixRepository mpPixRepository;

    public PixDto genPix(PixDto dto) {
        CreatePix createPix = new CreatePix(mpPixRepository);
        return createPix.execute(dto);
    }
}
