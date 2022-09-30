package com.ecomerce.price.exception;


import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecomerce.commons.exception.ExceptionResponse;

@RestControllerAdvice
public class ApiExceptionHandler {       
     
  
     @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ExceptionResponse> handleUnknownHostException(UnknownHostException ex) {
    	 ExceptionResponse response = new ExceptionResponse("Error de conexión","error-001",ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.PARTIAL_CONTENT);
    }
     
     @ExceptionHandler(BussinesException.class)
    public ResponseEntity<ExceptionResponse> handleBussinesException(BussinesException ex) {
    	 ExceptionResponse response = new ExceptionResponse("Error de validacion de Negocio",ex.getCode(),ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.PARTIAL_CONTENT);
    }
    
}
