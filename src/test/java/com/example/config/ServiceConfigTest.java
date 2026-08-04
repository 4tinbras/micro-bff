package com.example.config;

import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ServiceConfigTest {


    @Test
    void testTeamConfiguration() {
        Map<String, Object> items = new HashMap<>();
        items.put("services.jwtIssuerUri", "https://example.org");

        ApplicationContext ctx = ApplicationContext.run(items);
        ServiceConfig serviceConfig = ctx.getBean(ServiceConfig.class);

        assertEquals("https://example.org", serviceConfig.getJwtIssuerUri());

        ctx.close();
    }

}
