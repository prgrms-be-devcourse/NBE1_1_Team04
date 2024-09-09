package org.grepp.nbe1_1_team04.product.service;

import com.fasterxml.uuid.Generators;
import org.grepp.nbe1_1_team04.product.dto.ProductRequest;
import org.grepp.nbe1_1_team04.product.dto.ProductResponse;
import org.grepp.nbe1_1_team04.product.entity.ProductEntity;
import org.grepp.nbe1_1_team04.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void registerProduct(ProductRequest productRequest) {
        ProductEntity productEntity = productRequest.toEntity(productRequest);
        byte[] productId = createRandomUUID();

        productEntity.updateProductId(productId);
        productRepository.save(productEntity);
    }

    public List<ProductResponse> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(m -> m.toResponse(m)).collect(Collectors.toList());
    }

    public ProductResponse getProduct(byte[] productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productEntity.toResponse(productEntity);
    }

    public void updateProduct(ProductRequest productRequest, byte[] productId) {
        ProductEntity productEntity = productRequest.toEntity(productRequest);
        productEntity.updateProductId(productId);
        productRepository.save(productEntity);
    }

    public void deleteProduct(byte[] productId) {
        productRepository.deleteById(productId);
    }

    private byte[] createRandomUUID() {
        //순차 UUID 생성 및 설정
        UUID uuidV1 = Generators.timeBasedGenerator().generate();
        String[] uuidV1Parts = uuidV1.toString().split("-");
        String sequentialUUID = uuidV1Parts[2]+uuidV1Parts[1]+uuidV1Parts[0]+uuidV1Parts[3]+uuidV1Parts[4];

        String sequentialUuidV1 = String.join("", sequentialUUID);
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(Long.parseUnsignedLong(sequentialUuidV1.substring(0, 16), 16));
        bb.putLong(Long.parseUnsignedLong(sequentialUuidV1.substring(16), 16));
        return bb.array();
    }
}
