package com.quantweb.springserver.common.exception;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{
	private final CustomErrorCode errorCode;
}
