package com.ecomerce.price.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecomerce.commons.entity.Price;

public interface PriceDao extends JpaRepository<Price, Long> {

	 @Query("SELECT p FROM Price p WHERE p.product.id=:productId AND p.brand.id =:brandId AND :applicationDate BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC")
	 public List<Price> searchPrice(@Param("applicationDate") Date applicationDate,@Param("productId") Long productId, @Param("brandId") Long brandId);
}
