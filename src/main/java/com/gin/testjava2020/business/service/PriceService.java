package com.gin.testjava2020.business.service;

import java.util.Date;

import com.gin.testjava2020.exception.ProductNotFoundException;
import com.gin.testjava2020.model.PriceResponseBody;

/**
 * The interface Price service.
 */
public interface PriceService {

    /**
     * Gets price by apply date.
     *
     * @param applyDate the apply date
     * @param productId the product id
     * @param brandId   the brand id
     * @return the price by apply date
     * @throws ProductNotFoundException the product not found exception
     */
    PriceResponseBody getPriceByApplyDate(final Date applyDate, final Long productId, final Long brandId) throws ProductNotFoundException;

}
