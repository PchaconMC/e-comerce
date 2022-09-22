package com.ecomerce.price.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.price.entity.Price;
import com.ecomerce.price.service.IPriceService;

@RestController
public class PriceController {
	
	@Autowired
	private IPriceService priceService;
	
	@GetMapping("/list")
	public List<Price>list(){
		return priceService.findAll();
	}
	@GetMapping("/list/{id}")
	public Price detail(@PathVariable Long id) {
		return priceService.findById(id);
	}

}
