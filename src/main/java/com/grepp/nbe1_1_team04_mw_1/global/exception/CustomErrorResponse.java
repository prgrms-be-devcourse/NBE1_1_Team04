package com.grepp.nbe1_1_team04_mw_1.global.exception;

import lombok.*;
import org.springframework.http.ResponseEntity;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CustomErrorResponse {
    private int status;
    private String name;
    private String message;

    public static ResponseEntity<CustomErrorResponse> toResponse(CustomErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CustomErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .name(errorCode.name())
                        .message(errorCode.getMessage())
                        .build());
    }
}
