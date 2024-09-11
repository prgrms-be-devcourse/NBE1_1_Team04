package com.grepp.nbe1_1_clone_mw1.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_ADMIN("ADMIN", "관리자"),
    ROLE_USER("USER", "사용자");

    private final String role;
    private final String name;
}
