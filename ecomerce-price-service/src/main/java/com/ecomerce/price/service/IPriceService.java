package com.ecomerce.price.service;

import java.util.Date;
import java.util.List;

import com.ecomerce.commons.entity.Price;
import com.ecomerce.price.exception.BussinesException;

public interface IPriceService {
	
	public List<Price> findAll() throws BussinesException;
	public Price findById(Long id) throws BussinesException;
	public Price searchPrice(Date applicationDate,Long productId,Long brandId) throws BussinesException;

}
