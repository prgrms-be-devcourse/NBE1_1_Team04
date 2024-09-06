package com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request;

import java.util.List;

public record OrderRequestDTO(
        String email,
        String address,
        String postcode,
        List<OrderItemInfo> orderItems
) {

}


