package com.devcoruse.nbe1_1_team04.product.repository;

import static com.devcoruse.nbe1_1_team04.global.util.IdUtils.*;

import com.devcoruse.nbe1_1_team04.global.util.IdUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ProductId  implements Serializable {

    @Column(name = "product_id", length = 16)
    private byte[] value;

    public static ProductId from(byte[] value) {
        return new ProductId(value);
    }

    public static ProductId from(String value) {
        return new ProductId(hexToBytes(value));
    }

    public static ProductId generate() {
        return new ProductId(IdUtils.generateId());
    }

    @Override
    public String toString() {
        return bytesToHex(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return value.equals(productId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
