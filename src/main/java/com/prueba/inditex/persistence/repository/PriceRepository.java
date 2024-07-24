package com.prueba.inditex.persistence.repository;

import com.prueba.inditex.persistence.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {


    @Query(value = "SELECT * FROM PRICES WHERE PRODUCT_ID = :productId AND BRAND_ID = :brandId AND START_DATE <= :date AND END_DATE>=:date ORDER BY PRIORITY DESC  LIMIT 1", nativeQuery = true)
    public Optional<Price> getPrices(@Param("productId") Long productId, @Param("brandId") Long brandId, @Param("date") LocalDateTime date);
}
