package com.h2sm.smarthomebackend.api.controllers;

import com.h2sm.smarthomebackend.api.service.impl.AuthServiceImpl;
import com.h2sm.smarthomebackend.dtos.AuthenticateDTO;
import com.h2sm.smarthomebackend.dtos.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AuthenticateDTO dto) {
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @RequestMapping(value = "/hub")
    public ResponseEntity<?> loginHub(@RequestBody AuthenticateDTO dto){
        return ResponseEntity.ok(authService.doLoginHub(dto));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.doRegister(dto));
    }

}
