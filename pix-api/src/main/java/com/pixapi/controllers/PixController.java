package com.pixapi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class PixController {

    @PostMapping("/pix")
    public String hello() {
        return "Hello Spring Boot";
    }
}
