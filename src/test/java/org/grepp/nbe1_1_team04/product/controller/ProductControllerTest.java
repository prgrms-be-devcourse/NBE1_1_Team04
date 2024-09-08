package org.grepp.nbe1_1_team04.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.grepp.nbe1_1_team04.product.dto.ProductRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("상품 생성 api 테스트")
    void createProductTest() throws Exception {
        //given
        ProductRequest productRequest = new ProductRequest("상품1", "설명1", "COFFEE", 5000L);
        String productRequestBody = objectMapper.writeValueAsString(productRequest);

        //when
        MockHttpServletRequestBuilder requestBuilder = post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestBody);
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("상품 리스트 조회 api 테스트")
    void getProductsTest() throws Exception {
        //when
        MockHttpServletRequestBuilder requestBuilder = get("/products")
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("특정 상품 조회 api 테스트")
    void getProductTest() throws Exception {
        //when
        MockHttpServletRequestBuilder requestBuilder = get("/products/1")
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("특정 상품 수정 api 테스트")
    void updateProductTest() throws Exception {
        //given
        ProductRequest productRequest = new ProductRequest("상품1", "설명1", "COFFEE", 5000L);
        String productRequestBody = objectMapper.writeValueAsString(productRequest);

        //when
        MockHttpServletRequestBuilder requestBuilder = put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestBody);
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("특정 상품 삭제 api 테스트")
    void deleteProductTest() throws Exception {
        //when
        MockHttpServletRequestBuilder requestBuilder = delete("/products/1")
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(status().isOk());
    }


}
