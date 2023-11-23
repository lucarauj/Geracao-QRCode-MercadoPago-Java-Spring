package com.pixapi.domain.repositories;

import com.pixapi.domain.dto.PixDto;

public interface IPixRepository {

    PixDto create(PixDto dto);
}
