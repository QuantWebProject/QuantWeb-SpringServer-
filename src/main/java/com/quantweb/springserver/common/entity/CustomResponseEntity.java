package com.quantweb.springserver.common.entity;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

import jakarta.annotation.Nullable;

public class CustomResponseEntity<T> extends HttpEntity<T> {

	private final HttpStatus status;

	public CustomResponseEntity(HttpStatus status, @Nullable MultiValueMap<String, String> headers, @Nullable T body) {
		super(body, headers);
		Assert.notNull(status, "HttpStatus must not be null");
		this.status = status;
	}
}