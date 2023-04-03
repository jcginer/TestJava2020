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
class PriceControllerTI {

    public static final String APPLICATION_DATE = "2020-06-14T16:00:00";
    public static final double PRICE_VALUE = 25.45;
    public static final long PRODUCT_ID = 35455L;
    public static final long BRAND_ID = 1L;
    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String COMPLETE_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String START_DATE = "2020-06-14T15:00:00";
    public static final String END_DATE = "2020-06-14T18:30:00";
    public static final String ERROR_MESSAGE = "Product Not found for the required date-time";

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
        PriceResponseBody priceResponseBody = PriceResponseBody.builder().productId(PRODUCT_ID).brandId(BRAND_ID)
            .price(BigDecimal.valueOf(PRICE_VALUE)).finalPrice(BigDecimal.valueOf(PRICE_VALUE))
            .startDate(new SimpleDateFormat(DATE_PATTERN).parse(START_DATE))
            .endDate(new SimpleDateFormat(DATE_PATTERN).parse(END_DATE))
            .build();

        mockMvc.perform(get("/price")
                .contentType(MediaType.APPLICATION_JSON)
            .param("applyDate", APPLICATION_DATE)
            .param("brandId", String.valueOf(BRAND_ID))
            .param("productId", String.valueOf(PRODUCT_ID))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.productId").value(priceResponseBody.getProductId()))
            .andExpect(jsonPath("$.brandId").value(priceResponseBody.getBrandId()))
            .andExpect(jsonPath("$.price").value(priceResponseBody.getPrice()))
            .andExpect(jsonPath("$.finalPrice").value(priceResponseBody.getFinalPrice()))
            .andExpect(jsonPath("$.startDate").value(
                new SimpleDateFormat(COMPLETE_DATE_PATTERN).format(priceResponseBody.getStartDate())))
            .andExpect(jsonPath("$.endDate").value(
                new SimpleDateFormat(COMPLETE_DATE_PATTERN).format(priceResponseBody.getEndDate())));
    }

    @Test
    void priceExceptionTest() throws Exception {
        mockMvc.perform(get("/price")
                .contentType(MediaType.APPLICATION_JSON)
                .param("applyDate", APPLICATION_DATE)
                .param("brandId", String.valueOf(BRAND_ID))
                .param("productId", String.valueOf(1l))
            )
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
            .andExpect(result -> assertEquals(ERROR_MESSAGE, result.getResolvedException().getMessage()));
    }

}
