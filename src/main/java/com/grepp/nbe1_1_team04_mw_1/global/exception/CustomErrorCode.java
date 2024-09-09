package com.grepp.nbe1_1_team04_mw_1.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
    DO_NOT_ADD_PRODUCT(HttpStatus.BAD_REQUEST, "하나 이상의 상품을 추가해야 합니다.");

    private final HttpStatus httpStatus;
//    private final String code;
    private final String message;


}
