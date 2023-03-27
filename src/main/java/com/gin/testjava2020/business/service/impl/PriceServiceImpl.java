package com.gin.testjava2020.business.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.gin.testjava2020.business.mapper.PriceMapper;
import com.gin.testjava2020.business.service.PriceService;
import com.gin.testjava2020.exception.ProductNotFoundException;
import com.gin.testjava2020.model.PriceResponseBody;
import com.gin.testjava2020.persistence.PriceEntity;
import com.gin.testjava2020.persistence.repository.PriceRepository;
import lombok.AllArgsConstructor;

/**
 * The type Price service.
 */
@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private PriceRepository priceRepository;
    private PriceMapper priceMapper;

    @Override public PriceResponseBody getPriceByApplyDate(final Date applyDate, final Long productId, final Long brandId)
        throws ProductNotFoundException {

        List<PriceEntity> prices = null;
        try {
            prices = priceRepository.findByApplyDate(applyDate, productId, brandId);
        } catch(Exception e) {
            throw new ProductNotFoundException("Error during database access", e);
        }

        final PriceEntity price = prices.stream().sorted(Comparator.comparing(PriceEntity::getPriority, Comparator.reverseOrder())).findFirst().orElse(null);

        if(Objects.isNull(price)) {
            throw new ProductNotFoundException("Product Not found for the required date-time");
        }

        return priceMapper.map(price);
    }
}
