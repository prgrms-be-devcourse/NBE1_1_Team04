package com.grepp.nbe1_1_clone_mw1.user.controller.api;

import com.grepp.nbe1_1_clone_mw1.auth.model.CustomUserDetail;
import com.grepp.nbe1_1_clone_mw1.global.annotation.LoginUser;
import com.grepp.nbe1_1_clone_mw1.user.controller.dto.SignInRequest;
import com.grepp.nbe1_1_clone_mw1.user.controller.dto.SignUpRequest;
import com.grepp.nbe1_1_clone_mw1.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;
    private final SecurityContextHolderStrategy contextHolderStrategy =
            SecurityContextHolder.getContextHolderStrategy();

    private final HttpSessionSecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signInRequest, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = userService.login(signInRequest);

        var securityContext = this.contextHolderStrategy.createEmptyContext();
        securityContext.setAuthentication(authentication);
        this.securityContextRepository.saveContext(securityContext, request, response);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest, HttpServletResponse response, HttpServletRequest request) {
        userService.signUp(signUpRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        userService.logout();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> test(@LoginUser CustomUserDetail userDetail) {
        System.out.println(userDetail.getEmail());
        return ResponseEntity.ok().build();
    }

}
