package org.grepp.nbe1_1_team04.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.grepp.nbe1_1_team04.order.dto.OrderItemInfo;
import org.grepp.nbe1_1_team04.order.dto.OrderRequest;
import org.grepp.nbe1_1_team04.order.service.OrderService;
import org.grepp.nbe1_1_team04.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("주문 생성 api 테스트")
    void createOrderTest() throws Exception {
        //given
        OrderRequest orderRequest = new OrderRequest("test@test.com", "주소1", "우편번호1", new ArrayList<>());
        orderRequest.getOrderItems().add(new OrderItemInfo("1234".getBytes(), 50));
        orderRequest.getOrderItems().add(new OrderItemInfo("5678".getBytes(), 100));
        String orderRequestBody = objectMapper.writeValueAsString(orderRequest);

        //when
        MockHttpServletRequestBuilder requestBuilder = post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderRequestBody);
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("주문 리스트 조회 api 테스트")
    void getProductsTest() throws Exception {
        //when
        MockHttpServletRequestBuilder requestBuilder = get("/orders")
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("특정 주문 조회 api 테스트")
    void getProductTest() throws Exception {
        //when
        MockHttpServletRequestBuilder requestBuilder = get("/orders/1")
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("특정 주문 수정 api 테스트")
    void updateProductTest() throws Exception {
        //given
        OrderRequest orderRequest = new OrderRequest("test@test.com", "주소1", "우편번호1", new ArrayList<>());
        orderRequest.getOrderItems().add(new OrderItemInfo("1234".getBytes(), 50));
        orderRequest.getOrderItems().add(new OrderItemInfo("5678".getBytes(), 100));
        String orderRequestBody = objectMapper.writeValueAsString(orderRequest);

        //when
        MockHttpServletRequestBuilder requestBuilder = put("/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderRequestBody);
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("특정 주문 삭제 api 테스트")
    void deleteProductTest() throws Exception {
        //when
        MockHttpServletRequestBuilder requestBuilder = delete("/orders/1")
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(status().isOk());
    }
}
