package com.ecomerce.price.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomerce.price.entity.Price;

public interface PriceDao extends JpaRepository<Price, Long> {

}
