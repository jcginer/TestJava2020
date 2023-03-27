package com.gin.testjava2020.ws;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gin.testjava2020.business.service.PriceService;
import com.gin.testjava2020.exception.ProductNotFoundException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    private static final Date APPLY_DATE = new Date();
    private static final Long PRODUCT_ID = 1L;
    private static final Long BRAND_ID = 1L;
    private static final String PRODUCT_NOT_FOUND = "Product Not found";

    @InjectMocks
    private PriceController sut;

    @Mock
    private PriceService priceService;

    @Test
    void getPriceTest() throws ProductNotFoundException {
        sut.getPrice(APPLY_DATE, PRODUCT_ID, BRAND_ID);

        verify(priceService, only()).getPriceByApplyDate(APPLY_DATE, PRODUCT_ID, BRAND_ID);
    }

    @Test
    void getPriceExceptionTest() throws ProductNotFoundException {
        when(priceService.getPriceByApplyDate(APPLY_DATE, PRODUCT_ID, BRAND_ID)).thenThrow(new ProductNotFoundException(PRODUCT_NOT_FOUND));

        ProductNotFoundException pluginApiException = Assertions.assertThrows(ProductNotFoundException.class,
            ()-> sut.getPrice(APPLY_DATE, PRODUCT_ID, BRAND_ID));

        Assertions.assertEquals(PRODUCT_NOT_FOUND, pluginApiException.getMessage());
    }
}
