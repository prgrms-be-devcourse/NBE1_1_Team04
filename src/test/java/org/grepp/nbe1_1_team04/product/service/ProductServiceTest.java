package org.grepp.nbe1_1_team04.product.service;

import org.grepp.nbe1_1_team04.product.dto.ProductRequest;
import org.grepp.nbe1_1_team04.product.dto.ProductResponse;
import org.grepp.nbe1_1_team04.product.entity.ProductCategory;
import org.grepp.nbe1_1_team04.product.entity.ProductEntity;
import org.grepp.nbe1_1_team04.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품 추가 테스트")
    void registerProduct() {
        //given
        ProductRequest productRequest = new ProductRequest("상품1", "설명1", "COFFEE", 5000L);

        //when
        productService.registerProduct(productRequest);

        //then
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    @DisplayName("상품 리스트 조회 테스트")
    void getProductsTest() {
        //given
        List<ProductEntity> productEntities = List.of(
                new ProductEntity("1234".getBytes(), "상품1", "설명1", ProductCategory.COFFEE, 5000L),
                new ProductEntity("5678".getBytes(), "상품2", "설명2", ProductCategory.COFFEE, 3000L)
                );
        when(productRepository.findAll()).thenReturn(productEntities);

        //when
        List<ProductResponse> productResponses = productService.getProducts();

        //then
        assertThat(productResponses.size()).isEqualTo(2);
        assertThat(productResponses.get(0).getProductId()).isEqualTo("1234".getBytes());
        assertThat(productResponses.get(1).getProductId()).isEqualTo("5678".getBytes());
    }

    @Test
    @DisplayName("특정 상품 조회 테스트")
    void getProductTest() {
        //given
        byte[] productId = "1234".getBytes();
        Optional<ProductEntity> productEntity = Optional.of(new ProductEntity(productId, "상품1", "설명1", ProductCategory.COFFEE, 5000L));
        when(productRepository.findById(productId)).thenReturn(productEntity);

        //when
        ProductResponse productResponse = productService.getProduct(productId);

        //then
        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getProductId()).isEqualTo("1234".getBytes());
    }

    @Test
    @DisplayName("상품 업데이트 테스트")
    void updateProductTest() {
        //given
        byte[] productId = "1234".getBytes();
        ProductRequest productRequest = new ProductRequest("상품1", "설명1", "COFFEE", 5000L);

        //when
        productService.updateProduct(productRequest, productId);

        //then
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void deleteProductTest() {
        //given
        byte[] productId = "1234".getBytes();

        //when
        productService.deleteProduct(productId);

        //then
        verify(productRepository, times(1)).deleteById(productId);
    }

}
