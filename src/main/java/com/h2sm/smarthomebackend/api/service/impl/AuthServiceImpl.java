package com.h2sm.smarthomebackend.api.service.impl;

import com.h2sm.smarthomebackend.api.auth.provider.AuthProvider;
import com.h2sm.smarthomebackend.api.service.AuthService;
import com.h2sm.smarthomebackend.dtos.AuthenticateDTO;
import com.h2sm.smarthomebackend.dtos.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthProvider provider;

    public UsernamePasswordAuthenticationToken doLogin(AuthenticateDTO dto) {

        var auth = provider.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }

    @Override
    public UsernamePasswordAuthenticationToken doRegister(RegistrationDTO dto) {
        return null;
    }

}
