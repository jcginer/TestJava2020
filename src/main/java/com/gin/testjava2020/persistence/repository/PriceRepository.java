package com.gin.testjava2020.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gin.testjava2020.persistence.PriceEntity;

/**
 * The interface Price repository.
 */
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Find by apply date list.
     *
     * @param applyDate the apply date
     * @param productId the product id
     * @param brandId   the brand id
     * @return the list
     */
    @Query("FROM PriceEntity WHERE productId = :productId AND brandId  = :brandId "
        + "AND (startDate <= :applyDate AND :applyDate <= endDate)")
    List<PriceEntity> findByApplyDate(final Date applyDate, final Long productId, final Long brandId);

}
