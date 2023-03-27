package com.gin.testjava2020.business.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gin.testjava2020.business.mapper.PriceMapper;
import com.gin.testjava2020.exception.ProductNotFoundException;
import com.gin.testjava2020.model.PriceResponseBody;
import com.gin.testjava2020.persistence.Price;
import com.gin.testjava2020.persistence.repository.PriceRepository;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    private static final Date APPLY_DATE = new Date();
    private static final Long PRODUCT_ID = 1L;
    private static final Long BRAND_ID = 1L;
    private static final String PRODUCT_NOT_FOUND = "Product Not found for the required date-time";
    private static final String DB_ERROR = "Error during database access";
    public static final double PRICE_1 = 28.10;

    @InjectMocks
    private PriceServiceImpl sut;

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    @Test
    void getPriceByApplyDateTest() throws ProductNotFoundException {
        final Price price = createPrice(LocalDateTime.now(), 1, PRODUCT_ID, BRAND_ID, PRICE_1, 1);
        when(priceRepository.findByApplyDate(APPLY_DATE,  PRODUCT_ID, BRAND_ID)).thenReturn(Arrays.asList(price));

        final PriceResponseBody expectedPriceResponseBody = createPriceResponseBody(price);
        when(priceMapper.map(price)).thenReturn(expectedPriceResponseBody);

        final PriceResponseBody currentPriceResponseBody = sut.getPriceByApplyDate(APPLY_DATE, PRODUCT_ID, BRAND_ID);

        Assert.assertEquals(expectedPriceResponseBody, currentPriceResponseBody);
    }

    @Test
    void getPriceByApplyDateTwoResultsTest() throws ProductNotFoundException {
        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.now().plusHours(2);

        final Price price1 = createPrice(localDateTime1, 1, PRODUCT_ID, BRAND_ID, PRICE_1, 0);
        when(priceRepository.findByApplyDate(APPLY_DATE,  PRODUCT_ID, BRAND_ID)).thenReturn(Arrays.asList(price1));

        final Price price2 = createPrice(localDateTime2, 1, PRODUCT_ID, BRAND_ID, PRICE_1, 1);
        final PriceResponseBody expectedPriceResponseBody2 = createPriceResponseBody(price2);
        when(priceRepository.findByApplyDate(APPLY_DATE,  PRODUCT_ID, BRAND_ID)).thenReturn(Arrays.asList(price2));
        when(priceMapper.map(price2)).thenReturn(expectedPriceResponseBody2);

        final PriceResponseBody currentPriceResponseBody = sut.getPriceByApplyDate(APPLY_DATE, PRODUCT_ID, BRAND_ID);

        Assert.assertEquals(expectedPriceResponseBody2, currentPriceResponseBody);
    }

    @Test
    void getPriceByApplyDateExceptionTest() {
        when(priceRepository.findByApplyDate(APPLY_DATE,  PRODUCT_ID, BRAND_ID)).thenReturn(Collections.EMPTY_LIST);

        ProductNotFoundException pluginApiException = Assertions.assertThrows(ProductNotFoundException.class,
            ()-> sut.getPriceByApplyDate(APPLY_DATE, PRODUCT_ID, BRAND_ID));

        Assertions.assertEquals(PRODUCT_NOT_FOUND, pluginApiException.getMessage());
    }

    @Test
    void getPriceByApplyDateException2Test() {
        RuntimeException runtimeException = new RuntimeException();
        when(priceRepository.findByApplyDate(APPLY_DATE,  PRODUCT_ID, BRAND_ID)).thenThrow(runtimeException);

        ProductNotFoundException pluginApiException = Assertions.assertThrows(ProductNotFoundException.class,
            ()-> sut.getPriceByApplyDate(APPLY_DATE, PRODUCT_ID, BRAND_ID));

        Assertions.assertEquals(DB_ERROR, pluginApiException.getMessage());
        Assertions.assertEquals(runtimeException, pluginApiException.getCause());
    }

    private Price createPrice(final LocalDateTime localDateTime, final long days, final long productId, final long brandId, final double price, final int priority) {
        return Price.builder()
            .startDate(Date.from(localDateTime.atZone(ZoneId.systemDefault()).minusDays(days).toInstant()))
            .endDate(Date.from(localDateTime.atZone(ZoneId.systemDefault()).plusDays(days).toInstant()))
            .productId(productId)
            .brandId(brandId)
            .price(price)
            .priority(priority)
            .build();
    }

    private PriceResponseBody createPriceResponseBody(final Price price) {
        final PriceResponseBody expectedPriceResponseBody = new PriceResponseBody();
        expectedPriceResponseBody.productId(price.getProductId());
        expectedPriceResponseBody.brandId(price.getBrandId());
        expectedPriceResponseBody.price(BigDecimal.valueOf(price.getPrice()));
        expectedPriceResponseBody.startDate(price.getStartDate());
        expectedPriceResponseBody.endDate(price.getEndDate());
        expectedPriceResponseBody.finalPrice(BigDecimal.valueOf(price.getPrice()));
        
        return expectedPriceResponseBody;
    }

}
