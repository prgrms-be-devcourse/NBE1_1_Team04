package com.devcoruse.nbe1_1_team04.order.repository;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String address;

    private Integer zipCode;

    public static Address of(String address, Integer zipCode) {
        return new Address(address, zipCode);
    }
}
