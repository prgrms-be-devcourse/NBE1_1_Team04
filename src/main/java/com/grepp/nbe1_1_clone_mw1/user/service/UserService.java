package com.grepp.nbe1_1_clone_mw1.member.service;

import com.grepp.nbe1_1_clone_mw1.member.controller.dto.SignInRequest;
import com.grepp.nbe1_1_clone_mw1.member.controller.dto.SignUpRequest;
import com.grepp.nbe1_1_clone_mw1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void login(SignInRequest signInRequest) {

    }

    public void signUp(SignUpRequest signUpRequest) {

    }
}
