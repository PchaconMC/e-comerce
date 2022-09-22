package com.ecomerce.price.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.price.dao.PriceDao;
import com.ecomerce.price.entity.Price;

@Service
public class PriceServiceImpl implements IPriceService {
	@Autowired
	private PriceDao priceDao;

	@Override
	@Transactional(readOnly = true)
	public List<Price> findAll() {
		return (List<Price>) priceDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Price findById(Long id) {
		return priceDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Price searchPrice(Price price) {
		return null;
	}

}
