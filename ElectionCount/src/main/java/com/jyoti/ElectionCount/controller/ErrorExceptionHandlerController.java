/**
 * 
 */
package com.jyoti.ElectionCount.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jyoti.ElectionCount.model.DataNotFoundException;
import com.jyoti.ElectionCount.model.ErrorResponse;

/**
 * @author JyotiKumar
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class ErrorExceptionHandlerController {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(DataNotFoundException infe)  {
	
		ErrorResponse errorResp = null;
		ResponseEntity<ErrorResponse> responseEntity = null;
		// prepare the ErrorResponse
		errorResp = new ErrorResponse();
		errorResp.setStatus(HttpStatus.NOT_FOUND.value());
		errorResp.setMessage(infe.getMessage());
		errorResp.setTimeStamp(System.currentTimeMillis());
		// prepare ResponseEntity Object
		responseEntity = new ResponseEntity<ErrorResponse>(errorResp, HttpStatus.NOT_FOUND);
		// return the ResponseEntity
		return responseEntity;

	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		ErrorResponse errorResp = null;
		ResponseEntity<ErrorResponse> responseEntity = null;
		// create the ItemErrorResponse Object
		errorResp = new ErrorResponse();
		errorResp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResp.setMessage(ex.getMessage());
		errorResp.setTimeStamp(System.currentTimeMillis());
		// create the ResponseEntity Object
		responseEntity = new ResponseEntity<ErrorResponse>(errorResp, HttpStatus.INTERNAL_SERVER_ERROR);
		// return the responseEntity Object
		return responseEntity;
	}


}
