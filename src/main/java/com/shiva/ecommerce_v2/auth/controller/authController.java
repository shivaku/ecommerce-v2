package com.shiva.ecommerce_v2.auth.controller;

import com.shiva.ecommerce_v2.auth.service.UserAuthService;
import com.shiva.ecommerce_v2.auth.dto.LoginRequest;
import com.shiva.ecommerce_v2.auth.dto.LoginResponse;
import com.shiva.ecommerce_v2.auth.dto.UserAuthResponse;
import com.shiva.ecommerce_v2.auth.dto.UserRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class authController {

    private final UserAuthService userAuthService;

    @PostMapping("/register")
    public UserAuthResponse userRegistration(@RequestBody UserRequest userRequest) {

        return userAuthService.register(userRequest);

    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginReq) {
        return userAuthService.login(loginReq);
    }
}   

