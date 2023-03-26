package com.gin.testjava2020.business.service;

import java.util.Date;

import com.gin.testjava2020.exception.ProductNotFoundException;
import com.gin.testjava2020.model.PriceResponseBody;

public interface PriceService {

    PriceResponseBody getPriceByApplydate(final Date applyDate, final Long productId, final Long brandId) throws ProductNotFoundException;

}
