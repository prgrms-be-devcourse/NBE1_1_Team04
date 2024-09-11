package com.grepp.nbe1_1_clone_mw1.user.controller.api;

import com.grepp.nbe1_1_clone_mw1.user.controller.dto.SignInRequest;
import com.grepp.nbe1_1_clone_mw1.user.controller.dto.SignUpRequest;
import com.grepp.nbe1_1_clone_mw1.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/")
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

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        userService.logout();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public void test() {
        System.out.println();
    }
}
