package com.customerapp.CustomerAppApi.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class VersionController {
    @CrossOrigin(origins = "*")
    @GetMapping("version")
    public String getVersion()
    {
        return "1.0";
    }
}
