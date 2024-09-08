package com.grepp.nbe1_1_team04_mw_1.order.domain.dto.response;

import com.grepp.nbe1_1_team04_mw_1.order.domain.entity.Orders;
import com.grepp.nbe1_1_team04_mw_1.product.domain.dto.response.ProductUpdateResponseDTO;
import lombok.Getter;

import java.util.Base64;
import java.util.List;
@Getter
public class OrderSingleResponseDTO {
    private String orderId;

    private String email;

    private String address;

    private String postcode;

    private String orderStatus;

    List<ProductUpdateResponseDTO> products;

    public OrderSingleResponseDTO(Orders order, List<ProductUpdateResponseDTO> products) {
        this.orderId = Base64.getEncoder().encodeToString(order.getOrderId());
        this.email = order.getEmail();
        this.address = order.getAddress();
        this.postcode = order.getPostcode();
        this.orderStatus = order.getOrderStatus();
        this.products = products;
    }
}
