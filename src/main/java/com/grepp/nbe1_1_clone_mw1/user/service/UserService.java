package com.grepp.nbe1_1_clone_mw1.user.service;

import com.grepp.nbe1_1_clone_mw1.global.util.SessionUtil;
import com.grepp.nbe1_1_clone_mw1.user.controller.dto.SignInRequest;
import com.grepp.nbe1_1_clone_mw1.user.controller.dto.SignUpRequest;
import com.grepp.nbe1_1_clone_mw1.user.model.User;
import com.grepp.nbe1_1_clone_mw1.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public void login(SignInRequest signInRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(), signInRequest.getPassword()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void signUp(SignUpRequest signUpRequest) {
        User user = signUpRequest.toEntity();
        user.updatePassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
    }

    public void logout() {
        HttpSession session = SessionUtil.getSession();
        if (session != null) {
            session.invalidate();
        }
    }
}
