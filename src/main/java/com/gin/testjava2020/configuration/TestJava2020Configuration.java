package com.gin.testjava2020.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TestJava2020Configuration {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
