package com.h2sm.smarthomebackend.api.controllers;

import com.h2sm.smarthomebackend.api.service.impl.AuthServiceImpl;
import com.h2sm.smarthomebackend.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthenticatedUserDTO> login(@RequestBody AuthenticateUserDTO dto) {
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @RequestMapping(value = "/hub", method = RequestMethod.POST)
    public ResponseEntity<AuthenticatedHubDTO> loginHub(@RequestBody AuthenticateHubDTO dto){
        return ResponseEntity.ok(authService.doLoginHub(dto));
    }

}
