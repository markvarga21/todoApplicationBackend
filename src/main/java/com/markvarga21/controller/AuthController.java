package com.markvarga21.controller;

import com.markvarga21.dto.LoginRequest;
import com.markvarga21.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/token")
    public String token(@RequestBody LoginRequest userLogin) {
        log.info("Inside /token post endpoint");
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.userName(),
                        userLogin.password())
        );
        return this.tokenService.generateToken(authentication);
    }

    @GetMapping("/hello")
    public String hello() {
        return "HI";
    }
}
