package com.gin.testjava2020.configuration;

import java.util.TimeZone;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * The type Test java 2020 configuration.
 */
@Configuration
@ComponentScan(basePackages = {"com.gin.testjava2020.business.service.*", "com.gin.testjava2020.business.mapper"})
@EntityScan(basePackages = {"com.gin.testjava2020.persistence"})
@EnableJpaRepositories(basePackages = {"com.gin.testjava2020.persistence.repository"})
public class TestJava2020Configuration {

    /**
     * Jackson object mapper customization jackson 2 object mapper builder customizer.
     *
     * @return the jackson 2 object mapper builder customizer
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder ->
            jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }
}
