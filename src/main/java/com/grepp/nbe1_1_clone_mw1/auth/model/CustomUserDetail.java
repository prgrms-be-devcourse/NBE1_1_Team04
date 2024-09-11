package com.grepp.nbe1_1_clone_mw1.auth.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetail implements UserDetails {

    private String email;
    private String password;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    private CustomUserDetail(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static CustomUserDetail create(String email, String password, String role) {
        return new CustomUserDetail(email, password, role);
    }
}
