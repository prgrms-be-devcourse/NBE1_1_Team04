package com.grepp.nbe1_1_clone_mw1.user.service;

import com.grepp.nbe1_1_clone_mw1.user.controller.dto.SignInRequest;
import com.grepp.nbe1_1_clone_mw1.user.controller.dto.SignUpRequest;
import com.grepp.nbe1_1_clone_mw1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void login(SignInRequest signInRequest) {

    }

    public void signUp(SignUpRequest signUpRequest) {

    }
}
