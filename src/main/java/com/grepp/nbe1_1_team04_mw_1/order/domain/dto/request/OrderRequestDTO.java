package com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request;

import com.grepp.nbe1_1_team04_mw_1.order.domain.entity.Orders;
import lombok.Getter;

import java.util.List;
public record OrderRequestDTO(
        String email,
        String address,
        String postcode,
        List<OrderItemInfo> orderItems
) {
public Orders toEntity() {
    return Orders.builder()
            .email(this.email)
            .address(this.address)
            .postcode(this.postcode).build();
}
}


