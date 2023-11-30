package com.pixapi.controllers;

import com.pixapi.domain.dto.PixDto;
import com.pixapi.service.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/v1")
public class PixController {

    @Autowired
    private PixService pixService;

    @PostMapping("/pix")
    @Produces(MediaType.APPLICATION_JSON)
    public PixDto create(@RequestBody PixDto dto) {
        return pixService.genPix(dto);
    }
}
