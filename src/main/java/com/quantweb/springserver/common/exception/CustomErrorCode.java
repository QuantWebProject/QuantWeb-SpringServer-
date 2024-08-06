package com.quantweb.springserver.common.exception;

import org.springframework.http.HttpStatus;

public interface CustomErrorCode {

  String name();

  HttpStatus getHttpStatus();

  String getMessage();
}
