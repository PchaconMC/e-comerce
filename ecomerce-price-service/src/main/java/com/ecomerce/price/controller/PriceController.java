package com.ecomerce.price.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.commons.constants.DateConstant;
import com.ecomerce.commons.entity.Price;
import com.ecomerce.price.exception.BussinesException;
import com.ecomerce.price.service.IPriceService;

import io.swagger.annotations.Api;

/**
 * 
 * @author pchacon
 *
 */
@Api(tags = "Price API")
@RestController
public class PriceController {

	@Autowired
	private IPriceService priceService;

	@GetMapping("/list")
	public List<Price> listPrices() throws BussinesException {
		List<Price> price =  priceService.findAll();
		return price;
	}

	@GetMapping("/list/{id}")
	public Price listPriceId(@PathVariable Long id) throws BussinesException {
		Price price =  priceService.findById(id);
		return price;
	}

	@GetMapping("/search")
	public Price searchPrice(
			@RequestParam("applicationDate") @DateTimeFormat(pattern = DateConstant.FORMAT_IE) Date applicationDate,
			@RequestParam("productId") Long productId, @RequestParam("brandId") Long brandId) throws BussinesException {

		return priceService.searchPrice(applicationDate, productId, brandId);
	}
}
