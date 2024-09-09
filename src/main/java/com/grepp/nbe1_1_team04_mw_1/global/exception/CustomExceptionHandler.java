package com.grepp.nbe1_1_team04_mw_1.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CustomErrorResponse> handleCustomException(CustomException e) {
        e.printStackTrace();
        return CustomErrorResponse.toResponse(e.getErrorCode());
    }
}
