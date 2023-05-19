package com.h2sm.smarthomebackend.controllers;

import com.h2sm.smarthomebackend.service.impl.AuthServiceImpl;
import com.h2sm.smarthomebackend.dtos.RegistrationDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
@CrossOrigin
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
