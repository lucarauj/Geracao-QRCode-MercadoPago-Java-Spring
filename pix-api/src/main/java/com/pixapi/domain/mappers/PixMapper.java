package com.pixapi.domain.mappers;

import com.pixapi.domain.dto.PixDto;
import com.pixapi.domain.entities.PixBO;

public class PixMapper {

    public static PixDto toDTO(PixBO bo) {
        PixDto dto = new PixDto();

        dto.setId(bo.getId().toString());
        dto.setAmount(bo.getAmount());
        dto.setBase64(bo.getBase64());
        dto.setDescription(bo.getDescription());
        dto.setEmail(bo.getEmail());
        dto.setEmv(bo.getEmv());
        dto.setExpiration(bo.getExpiration());

        return dto;
    }

    public static PixBO toBO(PixDto dto) {
        return new PixBO(
                dto.getId(),
                dto.getAmount(),
                dto.getEmail(),
                dto.getDescription(),
                dto.getEmv(),
                dto.getBase64(),
                dto.getExpiration()
        );
    }
}
