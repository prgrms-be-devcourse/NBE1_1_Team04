package com.grepp.nbe1_1_clone_mw1.user.model;

import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class User {

    @Id
    private byte[] userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "postcode", nullable = false)
    private String postCode;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static User create(String email, String password, String address, String postCode) {
        return User.builder()
                .userId(UUIDUtil.createUUID())
                .email(email)
                .password(password)
                .address(address)
                .role(Role.USER)
                .postCode(postCode)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
