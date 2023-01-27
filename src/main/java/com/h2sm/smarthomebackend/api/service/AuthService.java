package com.h2sm.smarthomebackend.api.service;

import com.h2sm.smarthomebackend.dtos.AuthenticateDTO;
import com.h2sm.smarthomebackend.dtos.RegistrationDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthService {
    UsernamePasswordAuthenticationToken doLogin(AuthenticateDTO dto);
    UsernamePasswordAuthenticationToken doRegister(RegistrationDTO dto);
}
