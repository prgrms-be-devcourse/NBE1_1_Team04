package com.devcoruse.nbe1_1_team04.order.domain;

import com.devcoruse.nbe1_1_team04.order.repository.OrderId;
import com.devcoruse.nbe1_1_team04.order.repository.OrderItem;
import com.devcoruse.nbe1_1_team04.order.repository.OrderItemRepository;
import com.devcoruse.nbe1_1_team04.product.domain.ProductReader;
import com.devcoruse.nbe1_1_team04.product.repository.Product;
import com.devcoruse.nbe1_1_team04.product.repository.ProductId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemAppender {

    private final ProductReader productReader;
    private final OrderItemRepository orderItemRepository;

    public void append(OrderId orderId, List<OrderItemInfo> orderItemInfos) {
        for (OrderItemInfo orderItemInfo : orderItemInfos) {
            Product product = productReader.read(ProductId.from(orderItemInfo.productId().getBytes()));
            OrderItem orderItem = OrderItem.create(orderId, product, orderItemInfo);
            orderItemRepository.save(orderItem);
        }
    }
}
