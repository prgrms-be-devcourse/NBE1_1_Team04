package com.grepp.nbe1_1_clone_mw1.user.controller.api;

import com.grepp.nbe1_1_clone_mw1.user.controller.dto.SignInRequest;
import com.grepp.nbe1_1_clone_mw1.user.controller.dto.SignUpRequest;
import com.grepp.nbe1_1_clone_mw1.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserRestController {

    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        userService.login(signInRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
        return ResponseEntity.ok().build();
    }
}
