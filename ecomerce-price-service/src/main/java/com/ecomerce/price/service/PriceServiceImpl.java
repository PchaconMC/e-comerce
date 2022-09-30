package com.ecomerce.price.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.price.dao.PriceDao;
import com.ecomerce.price.exception.BussinesException;
import com.ecomerce.commons.entity.Price;

@Service
public class PriceServiceImpl implements IPriceService {
	@Autowired
	private PriceDao priceDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Price> findAll() throws BussinesException {
		List<Price> price = priceDao.findAll();
		if(price ==null || price.isEmpty()) {
			BussinesException exception = new BussinesException("100","Error de validación, Lista de Precio no existe", HttpStatus.PRECONDITION_FAILED);
			throw exception;	
		}
		return price;
	}

	@Override
	@Transactional(readOnly = true)
	public Price findById(Long id) throws BussinesException {
		Price price = priceDao.findById(id).orElse(null);
		if(price ==null) {
			BussinesException exception = new BussinesException("100","Error de validación, Precio no existe", HttpStatus.PRECONDITION_FAILED);
			throw exception;	
		}
		return price;
	}

	@Override
	@Transactional(readOnly = true)
	public Price searchPrice(Date applicationDate, Long productId, Long brandId) throws BussinesException  {
		Price price;
		try {
			price = entityManager
					.createQuery("SELECT p FROM Price p WHERE p.product.id=:productId AND p.brand.id =:brandId"
							+ " AND :applicationDate BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC",
							Price.class)
					.setParameter("applicationDate", applicationDate).setParameter("productId", productId)
					.setParameter("brandId", brandId).setMaxResults(1).getSingleResult();
		} catch (Exception ex) {
			BussinesException exception = new BussinesException("100","Error de validación, Precio no existe", HttpStatus.PRECONDITION_FAILED);
			throw exception;
		}
		return price;
	}

}
