package com.devcoruse.nbe1_1_team04.order.repository;

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
public class OrderId implements Serializable {

    @Column(name = "order_id", length = 16)
    private byte[] value;

    public static OrderId generate() {
        return new OrderId(IdUtils.generateId());
    }

    public static OrderId from(String value) {
        return new OrderId(IdUtils.hexToBytes(value));
    }

    public static OrderId from(byte[] value) {
        return new OrderId(value);
    }

    @Override
    public String toString() {
        return IdUtils.bytesToHex(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId = (OrderId) o;
        return value.equals(orderId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
