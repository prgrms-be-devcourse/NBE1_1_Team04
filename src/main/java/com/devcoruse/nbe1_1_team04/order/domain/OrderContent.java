package com.devcoruse.nbe1_1_team04.order.domain;

public record OrderContent(
        String email,
        String address,
        Integer zipCode,
        Integer price
) {

}
