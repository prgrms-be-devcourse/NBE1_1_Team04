package com.grepp.nbe1_1_clone_mw1.product.service;


import com.grepp.nbe1_1_clone_mw1.global.util.FileUtil;
import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import com.grepp.nbe1_1_clone_mw1.order.repository.OrderItemRepository;
import com.grepp.nbe1_1_clone_mw1.product.controller.dto.ProductResponse;
import com.grepp.nbe1_1_clone_mw1.product.controller.dto.UpdateProductRequest;
import com.grepp.nbe1_1_clone_mw1.product.model.Category;
import com.grepp.nbe1_1_clone_mw1.product.model.Product;
import com.grepp.nbe1_1_clone_mw1.product.model.ProductImage;
import com.grepp.nbe1_1_clone_mw1.product.repository.ProductImageRepository;
import com.grepp.nbe1_1_clone_mw1.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

  private final ProductRepository productRepository;
  private final OrderItemRepository orderItemRepository;
  private final ProductImageRepository productImageRepository;

  @Override
  public List<ProductResponse> getProductsByCategory(Category category) {

    return productRepository.findByCategory(category).stream().map(ProductResponse::new).toList();
  }

  @Override
  public List<ProductResponse> getAllProducts() {
    return productRepository.findAll().stream().map(ProductResponse::new).toList();
  }

  @Override
  public Product createProduct(String productName, Category category, long price) {
    var product = Product.create(productName, category, price);
    return productRepository.save(product);
  }

  @Transactional
  @Override
  public Product createProduct(String productName, Category category, long price, String description, MultipartFile[] uploadImage) {
    var product = Product.create(productName, category, price, description);
    Product newProduct = productRepository.save(product);
    try {
      List<ProductImage> saveFiles = FileUtil.saveFiles(uploadImage);
      saveProductImage(saveFiles, newProduct.getProductId());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return newProduct;
  }

  @Transactional
  @Override
  public String deleteProduct(String productId) {
    orderItemRepository.deleteByProducts_ProductId(UUIDUtil.hexStringToByteArray(productId));
    productRepository.deleteByProductId(UUIDUtil.hexStringToByteArray(productId));
    return "delete success";
  }

  @Transactional
  @Override
  public Product updateProduct(String productId, UpdateProductRequest updateProductRequest) {
    Product oldProduct = productRepository.findById(UUIDUtil.hexStringToByteArray(productId)).orElseThrow(()->new RuntimeException("Product not found"));
    Product newProduct = Product.builder()
            .productId(oldProduct.getProductId())
            .productName(updateProductRequest.productName()==null ? oldProduct.getProductName() : updateProductRequest.productName())
            .price(updateProductRequest.price()==null ? oldProduct.getPrice() : updateProductRequest.price())
            .description(updateProductRequest.description()==null ? oldProduct.getDescription() : updateProductRequest.description())
            .category(updateProductRequest.category()==null ? oldProduct.getCategory() : updateProductRequest.category())
            .createdAt(oldProduct.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

    return productRepository.save(newProduct);
  }

  public void saveProductImage(List<ProductImage> productImages, byte[] productId) {
    if(productImages == null || productImages.isEmpty()) return;
    for(ProductImage fileEntity : productImages){
      fileEntity.updateProduct(productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product not found")));
    }
    productImageRepository.saveAll(productImages);
  }

  @Override
  public ResponseEntity<Resource> getProductImage(String productId) {
    ProductImage productImage = productImageRepository.findFirstByProducts_ProductId(UUIDUtil.hexStringToByteArray(productId)).orElseThrow(()->new RuntimeException("Product Image not found"));
    try {
      Path path = Paths.get(productImage.getSavedPath()).normalize();
      Resource resource = new UrlResource(path.toUri());
      if (resource.exists() && resource.isReadable()) {
        // 파일을 찾았을 때, 파일 데이터를 ResponseEntity로 반환
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path)) // MIME 타입 설정
                .body(resource);
      } else {
        // 파일이 존재하지 않을 경우 404 응답
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }


}
