package com.gin.testjava2020.ws;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.gin.testjava2020.exception.ProductNotFoundException;
import com.gin.testjava2020.model.PriceResponseBody;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PriceControllerTI {

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    @Container
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11-alpine")
        .withDatabaseName("integration-tests-db")
        .withPassword("inmemory")
        .withUsername("inmemory");

    @Test
    void priceTest() throws Exception {
        PriceResponseBody priceResponseBody = PriceResponseBody.builder().productId(35455L).brandId(1L)
            .price(BigDecimal.valueOf(25.45)).finalPrice(BigDecimal.valueOf(25.45))
            .startDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2020-06-14T15:00:00"))
            .endDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2020-06-14T18:30:00"))
            .build();

        mockMvc.perform(get("/price")
                .contentType(MediaType.APPLICATION_JSON)
            .param("applyDate", "2020-06-14T16:00:00")
            .param("brandId", String.valueOf(1L))
            .param("productId", String.valueOf(35455L))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.productId").value(priceResponseBody.getProductId()))
            .andExpect(jsonPath("$.brandId").value(priceResponseBody.getBrandId()))
            .andExpect(jsonPath("$.price").value(priceResponseBody.getPrice()))
            .andExpect(jsonPath("$.finalPrice").value(priceResponseBody.getFinalPrice()))
            .andExpect(jsonPath("$.startDate").value(
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(priceResponseBody.getStartDate())))
            .andExpect(jsonPath("$.endDate").value(
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(priceResponseBody.getEndDate())));
    }

    @Test
    void priceExceptionTest() throws Exception {
        mockMvc.perform(get("/price")
                .contentType(MediaType.APPLICATION_JSON)
                .param("applyDate", "2020-06-14T16:00:00")
                .param("brandId", String.valueOf(1L))
                .param("productId", String.valueOf(1L))
            )
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
            .andExpect(result -> assertEquals("Product Not found for the required date-time", result.getResolvedException().getMessage()));
    }

}
