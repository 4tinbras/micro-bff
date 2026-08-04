package com.example.config;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("services")
public class ServiceConfig {

    private String jwtIssuerUri;

    public void setJwtIssuerUri(String jwtIssuerUri) {
        this.jwtIssuerUri = jwtIssuerUri;
    }

    public String getJwtIssuerUri() {
        return this.jwtIssuerUri;
    }
}
