package com.grepp.nbe1_1_team04_mw_1.order.domain.dto.response;


import com.grepp.nbe1_1_team04_mw_1.order.domain.entity.Orders;
import lombok.Getter;

import java.util.Base64;
@Getter
public class OrderResponseDTO {
    private String orderId;

    private String email;

    private String address;

    private String postcode;

    private String orderStatus;

    public OrderResponseDTO(Orders order) {
        this.orderId = Base64.getEncoder().encodeToString(order.getOrderId());
        this.email = order.getEmail();
        this.address = order.getAddress();
        this.postcode = order.getPostcode();
        this.orderStatus = order.getOrderStatus();
    }
}
