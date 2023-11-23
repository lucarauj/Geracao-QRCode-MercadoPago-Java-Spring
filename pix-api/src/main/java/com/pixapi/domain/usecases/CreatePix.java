package com.pixapi.domain.usecases;

import com.pixapi.domain.dto.PixDto;
import com.pixapi.domain.entities.PixBO;
import com.pixapi.domain.mappers.PixMapper;
import com.pixapi.domain.repositories.IPixRepository;

public class CreatePix {

    private IPixRepository pixRepository;

    public CreatePix(IPixRepository pixRepository) {
        this.pixRepository = pixRepository;
    }

    public PixDto execute(PixDto pixDto) {
        PixBO bo = PixMapper.toBO(pixDto);
        return pixRepository.create(pixDto);
    }
}
