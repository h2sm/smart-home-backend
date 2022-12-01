package com.h2sm.smarthomebackend.api.service;

import com.h2sm.smarthomebackend.api.User;
import com.h2sm.smarthomebackend.api.auth.provider.AuthProvider;
import com.h2sm.smarthomebackend.api.repository.AuthRepository;
import com.h2sm.smarthomebackend.dtos.AuthenticateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private AuthProvider provider;

    public UsernamePasswordAuthenticationToken doLogin(AuthenticateDTO dto) {

        var auth = provider.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }

    public User doRegister(User user) {
        //authRepository.save(user);
        return null;
    }
}
