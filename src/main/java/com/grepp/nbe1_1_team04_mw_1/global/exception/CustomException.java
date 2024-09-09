package com.grepp.nbe1_1_team04_mw_1.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final CustomErrorCode errorCode;
}
