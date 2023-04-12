package com.h2sm.smarthomebackend.api.service.impl;

import com.h2sm.smarthomebackend.api.auth.provider.AuthProvider;
import com.h2sm.smarthomebackend.api.entities.UserEntity;
import com.h2sm.smarthomebackend.api.service.AuthService;
import com.h2sm.smarthomebackend.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthProvider provider;

    public AuthenticatedUserDTO doLogin(AuthenticateUserDTO dto) {
        var auth = provider.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new AuthenticatedUserDTO((String) auth.getCredentials(), ((UserEntity) auth.getPrincipal()).getUserLogin());
    }

    public AuthenticatedHubDTO doLoginHub(AuthenticateHubDTO dto){
        var auth = provider.authenticateHub(
                new UsernamePasswordAuthenticationToken(dto.getHubAuthId(), dto.getHubPass()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new AuthenticatedHubDTO((String) auth.getCredentials());
    }

    @Override
    public UsernamePasswordAuthenticationToken doRegister(RegistrationDTO dto) {
        return null;
    }

}
