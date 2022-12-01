package com.h2sm.smarthomebackend.api.auth.controllers;

import com.h2sm.smarthomebackend.api.User;
import com.h2sm.smarthomebackend.api.service.AuthService;
import com.h2sm.smarthomebackend.dtos.AuthenticateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticateDTO dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return authService.doRegister(user);
    }

}
