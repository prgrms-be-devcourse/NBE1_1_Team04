package org.example.coffee.exception.exceptionhandler;

import org.example.coffee.exception.EmailMismatchForOrderException;
import org.example.coffee.exception.OrderNotFoundException;
import org.example.coffee.exception.ProductNotFoundException;
import org.example.coffee.exception.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class, OrderNotFoundException.class})
    public ResponseEntity<ErrorDTO> notFoundException(RuntimeException e) {
        ErrorDTO errorDTO = new ErrorDTO(
                HttpStatus.NOT_FOUND.value(),
                ProductNotFoundException.class.toString(),
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailMismatchForOrderException.class)
    public ResponseEntity<ErrorDTO> emailMismatchForOrderException(EmailMismatchForOrderException e) {
        ErrorDTO errorDTO = new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                ProductNotFoundException.class.toString(),
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
