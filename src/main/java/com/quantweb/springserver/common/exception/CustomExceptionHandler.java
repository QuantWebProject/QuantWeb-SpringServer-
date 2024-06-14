package com.quantweb.springserver.common.exception;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.quantweb.springserver.common.entity.CustomResponseEntity;

@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(CustomException.class)
	protected HttpEntity<JSONObject> handleCustomException(CustomException e) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", e.getErrorCode().getMessage());
		return CustomResponseEntity.of(e.getErrorCode().getHttpStatus(), new HttpHeaders(), jsonObject);
	}
}