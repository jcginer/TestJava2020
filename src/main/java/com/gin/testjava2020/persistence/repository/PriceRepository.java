package com.gin.testjava2020.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gin.testjava2020.persistence.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("FROM Price WHERE productId = :productId AND brandId  = :brandId "
        + "AND (startDate <= :applyDate AND :applyDate <= endDate)")
    List<Price> findByApplyDate(final Date applyDate, final Long productId, final Long brandId);

}
