package com.quantweb.springserver.common.exception;

import com.quantweb.springserver.common.entity.CustomResponseEntity;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected HttpEntity<JSONObject> handleCustomException(CustomException e) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", e.getErrorCode().getMessage());
        return CustomResponseEntity.of(e.getErrorCode().getHttpStatus(), new HttpHeaders(),
            jsonObject);
    }
}