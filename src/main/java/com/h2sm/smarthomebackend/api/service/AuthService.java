package com.h2sm.smarthomebackend.api.service;

import com.h2sm.smarthomebackend.dtos.AuthenticateUserDTO;
import com.h2sm.smarthomebackend.dtos.AuthenticatedUserDTO;
import com.h2sm.smarthomebackend.dtos.RegistrationDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthService {
    AuthenticatedUserDTO doLogin(AuthenticateUserDTO dto);
    UsernamePasswordAuthenticationToken doRegister(RegistrationDTO dto);
}
