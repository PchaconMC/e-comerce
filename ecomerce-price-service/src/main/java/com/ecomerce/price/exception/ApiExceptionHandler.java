package com.ecomerce.price.exception;


import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecomerce.commons.constants.ErrorMessageConstant;
import com.ecomerce.commons.exception.ExceptionResponse;
/**
 * 
 * @author pchacon
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler {       
     
  
     @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ExceptionResponse> handleUnknownHostException(UnknownHostException ex) {
    	 ExceptionResponse response = new ExceptionResponse(ErrorMessageConstant.CODE_001_TITLE,ErrorMessageConstant.CODE_001,ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.PARTIAL_CONTENT);
    }
     
     @ExceptionHandler(BussinesException.class)
    public ResponseEntity<ExceptionResponse> handleBussinesException(BussinesException ex) {
    	 ExceptionResponse response = new ExceptionResponse(ErrorMessageConstant.CODE_002_TITLE,ex.getCode(),ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.PARTIAL_CONTENT);
    }
    
}
