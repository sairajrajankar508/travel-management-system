//package com.travel.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.travel.entity.SystemConfig;
//import com.travel.repository.SystemConfigRepository;
//
//@Service
//public class ConfigService {
//
//    @Autowired
//    private SystemConfigRepository systemConfigRepository;
//
//    // SAVE CONFIG
//    public SystemConfig save(SystemConfig config) {
//        return systemConfigRepository.save(config);
//    }
//
//    // GET ALL CONFIGS
//    public List<SystemConfig> getAll() {
//        return systemConfigRepository.findAll();
//    }
//}



package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.SystemConfig;
import com.travel.repository.SystemConfigRepository;

@Service
public class ConfigService {

    @Autowired
    private SystemConfigRepository systemConfigRepository;

    // =====================================================
    // SAVE / UPDATE CONFIG
    // =====================================================
    public SystemConfig save(SystemConfig config) {

        if (config.getConfigKey() == null || config.getConfigKey().isEmpty()) {
            throw new RuntimeException("Config key cannot be empty");
        }

        return systemConfigRepository.save(config);
    }

    // =====================================================
    // GET ALL CONFIGS
    // =====================================================
    public List<SystemConfig> getAll() {
        return systemConfigRepository.findAll();
    }
}