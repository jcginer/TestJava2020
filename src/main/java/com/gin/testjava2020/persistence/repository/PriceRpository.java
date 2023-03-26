package com.gin.testjava2020.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gin.testjava2020.persistence.Price;

public interface PriceRpository extends JpaRepository<Price, Long> {
}
