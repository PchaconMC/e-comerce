package com.ecomerce.price.service;

import java.util.List;

import com.ecomerce.price.entity.Price;

public interface IPriceService {
	
	public List<Price> findAll();
	public Price findById(Long id);
	public Price searchPrice(Price price);

}
