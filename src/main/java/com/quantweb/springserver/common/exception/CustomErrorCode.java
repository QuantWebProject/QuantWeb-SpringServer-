package com.quantweb.springserver.global.common.exception;

import org.springframework.http.HttpStatus;

public interface CustomErrorCode {

    String name();

    HttpStatus getHttpStatus();

    String getMessage();
}
