package com.ecomerce.price.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author pchacon
 *
 */
@Getter
@Setter
public class BussinesException extends Exception {
	
	private long id;
	private String code;
	private HttpStatus httpStatus;
	
	public BussinesException(long id, String code, String message, HttpStatus httpStatus) {
		super(message);
		this.id = id;
		this.code= code;
		this.httpStatus = httpStatus;
	}
	public BussinesException(String code, String message, HttpStatus httpStatus) {
		super(message);
		this.code= code;
		this.httpStatus = httpStatus;
	}
	public BussinesException(String message, Throwable cause) {
		super(message, cause);
	}	
	
	private static final long serialVersionUID = 1L;
}
