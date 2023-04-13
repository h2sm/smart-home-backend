package com.h2sm.smarthomebackend.api.controllers;

import com.h2sm.smarthomebackend.api.service.impl.AuthServiceImpl;
import com.h2sm.smarthomebackend.dtos.RegistrationDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegistrationController {
    private final AuthServiceImpl authService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.doRegister(dto));
    }

    @RequestMapping(value = "/hub", method = RequestMethod.POST)
    public ResponseEntity<?> registerHub(@RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.doRegister(dto));
    }
}
