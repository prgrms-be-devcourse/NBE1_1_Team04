package org.grepp.nbe1_1_team04.order.service;

import org.grepp.nbe1_1_team04.order.dto.OrderItemInfo;
import org.grepp.nbe1_1_team04.order.dto.OrderRequest;
import org.grepp.nbe1_1_team04.order.dto.OrderResponse;
import org.grepp.nbe1_1_team04.order.entity.OrderEntity;
import org.grepp.nbe1_1_team04.order.entity.OrderItemsEntity;
import org.grepp.nbe1_1_team04.order.entity.OrderStatus;
import org.grepp.nbe1_1_team04.order.repository.OrderItemsRepository;
import org.grepp.nbe1_1_team04.order.repository.OrderRepository;
import org.grepp.nbe1_1_team04.product.entity.ProductCategory;
import org.grepp.nbe1_1_team04.product.entity.ProductEntity;
import org.grepp.nbe1_1_team04.product.repository.ProductRepository;
import org.grepp.nbe1_1_team04.util.UUIDUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private UUIDUtil uuidUtil;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemsRepository orderItemsRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("주문 추가 테스트")
    void registerOrder() {
        //given
        when(uuidUtil.createRandomUUID()).thenReturn("1234".getBytes());
        OrderRequest orderRequest = new OrderRequest("test@test.com", "주소1", "우편번호1", new ArrayList<>());
        orderRequest.getOrderItems().add(new OrderItemInfo("1234".getBytes(), 50));
        orderRequest.getOrderItems().add(new OrderItemInfo("5678".getBytes(), 100));
        when(productRepository.findById(any())).thenReturn(Optional.of(new ProductEntity("이름1", "설명1", ProductCategory.COFFEE, 5000L)));

        //when
        orderService.createOrder(orderRequest);

        //then
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
        verify(orderItemsRepository, times(orderRequest.getOrderItems().size())).save(any(OrderItemsEntity.class));
    }

    @Test
    @DisplayName("주문 리스트 조회 테스트")
    void getOrdersTest() {
        //given
        OrderEntity newOrder1 = new OrderEntity("1234".getBytes(), "test@test.com", "주소1", "우편번호1", OrderStatus.valueOf("ORDERED"));
        OrderEntity newOrder2 = new OrderEntity("5678".getBytes(), "test@test.com", "주소1", "우편번호1", OrderStatus.valueOf("DELIVERING"));
        ProductEntity newProduct1 = new ProductEntity("1235".getBytes(), "이름1", "설명1", ProductCategory.COFFEE, 5000L);
        ProductEntity newProduct2 = new ProductEntity("1237".getBytes(), "이름2", "설명2", ProductCategory.COFFEE, 3000L);

        List<OrderEntity> orderEntities = List.of(newOrder1, newOrder2);
        List<OrderItemsEntity> orderItemEntities = List.of(
                new OrderItemsEntity(5000, 3, ProductCategory.COFFEE, newOrder1, newProduct1),
                new OrderItemsEntity(3000, 5, ProductCategory.COFFEE, newOrder1, newProduct2)
        );

        when(orderRepository.findByEmail("test@test.com")).thenReturn(orderEntities);
        when(orderItemsRepository.findByOrder(newOrder1)).thenReturn(orderItemEntities);

        //when
        List<OrderResponse> orderResponses = orderService.getOrders("test@test.com");

        //then
        assertThat(orderResponses.size()).isEqualTo(2);
        assertThat(orderResponses.get(0).getOrderItems().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("특정 주문 조회 테스트")
    void getProductTest() {
        //given
        byte[] orderId = "1234".getBytes();

        Optional<OrderEntity> orderEntity = Optional.of(new OrderEntity(orderId, "test@test.com", "주소1", "우편번호1",OrderStatus.valueOf("ORDERED")));
        ProductEntity newProduct1 = new ProductEntity("1235".getBytes(), "이름1", "설명1", ProductCategory.COFFEE, 5000L);
        ProductEntity newProduct2 = new ProductEntity("1237".getBytes(), "이름2", "설명2", ProductCategory.COFFEE, 3000L);
        List<OrderItemsEntity> orderItemEntities = List.of(
                new OrderItemsEntity(5000, 3, ProductCategory.COFFEE, orderEntity.get(), newProduct1),
                new OrderItemsEntity(3000, 5, ProductCategory.COFFEE, orderEntity.get(), newProduct2)
        );
        when(orderRepository.findById(orderId)).thenReturn(orderEntity);
        when(orderItemsRepository.findByOrder(orderEntity.get())).thenReturn(orderItemEntities);

        //when
        OrderResponse orderResponse = orderService.getOrder(orderId);

        //then
        assertThat(orderResponse).isNotNull();
        assertThat(orderResponse.getOrderItems().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("주문 업데이트 테스트")
    void updateProductTest() {
        //given
        byte[] orderId = "1234".getBytes();
        OrderRequest orderRequest = new OrderRequest("test@test.com", "주소1", "우편번호1", new ArrayList<>());
        orderRequest.getOrderItems().add(new OrderItemInfo("1234".getBytes(), 50));
        orderRequest.getOrderItems().add(new OrderItemInfo("5678".getBytes(), 100));

        //when
        orderService.updateOrder(orderRequest, orderId);

        //then
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
        verify(orderItemsRepository, times(orderRequest.getOrderItems().size())).save(any(OrderItemsEntity.class));
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void deleteProductTest() {
        //given
        byte[] orderId = "1234".getBytes();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(new OrderEntity(orderId, "test@test.com", "주소1", "우편번호1",OrderStatus.valueOf("ORDERED"))));

        //when
        orderService.deleteOrder(orderId);

        //then
        verify(orderRepository, times(1)).deleteById(orderId);
        verify(orderItemsRepository).deleteByOrder(any(OrderEntity.class));
    }
}
