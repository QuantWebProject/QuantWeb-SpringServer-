package com.quantweb.springserver.common.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.quantweb.springserver.common.entity.CustomResponseEntity;

@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(CustomException.class)
	protected HttpEntity<String> handleCustomException(CustomException e) {
		return new CustomResponseEntity<>(e.getErrorCode().getHttpStatus(), new HttpHeaders(), e.getErrorCode().getMessage());
	}
}