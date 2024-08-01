package com.quantweb.springserver.common.entity;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import jakarta.annotation.Nullable;

public class CustomResponseEntity<T> extends HttpEntity<T> {

	private final HttpStatus status;

	public static <T> CustomResponseEntity<T> of(HttpStatus status, @Nullable MultiValueMap<String, String> headers, @Nullable T body) {
		if (headers == null) {
			headers = new LinkedMultiValueMap<>();
		}
		headers.add("content-type", "application/json");
		headers.add("accept", "application/json");
		return new CustomResponseEntity<>(status, headers, body);
	}

	private CustomResponseEntity(HttpStatus status, @Nullable MultiValueMap<String, String> headers, @Nullable T body) {
		super(body, headers);
		Assert.notNull(status, "HttpStatus must not be null");
		this.status = status;
	}
}