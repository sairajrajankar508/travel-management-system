package com.travel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SystemConfig {

    @Id
    private String configKey;

    private String configValue;

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

    
    
    
    
}