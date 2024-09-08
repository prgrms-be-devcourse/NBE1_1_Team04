package org.example.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {
    private final int status;
    private final String error;
    private final String message;
    private final LocalDateTime timestamp;
}
