package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.travel.entity.SystemConfig;
import com.travel.service.ConfigService;

@RestController
@RequestMapping("/admin/config")
@CrossOrigin("*")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    // CREATE / UPDATE CONFIG
    @PostMapping
    public SystemConfig save(@RequestBody SystemConfig config) {
        return configService.save(config);
    }

    // GET ALL CONFIGS
    @GetMapping
    public List<SystemConfig> getAll() {
        return configService.getAll();
    }
}