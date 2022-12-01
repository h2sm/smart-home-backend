package com.h2sm.smarthomebackend.api.auth.provider;

import com.h2sm.smarthomebackend.api.entities.UserEntity;
import com.h2sm.smarthomebackend.api.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;


@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {
    public static final String INCORRECT_DATA = "Provided email or password is incorrect";
    public static final String USER_NOT_FOUND = "User not found";
    private AuthRepository authRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UsernamePasswordAuthenticationToken authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        var userEntity = authRepository.getUserEntityByUserLogin(email);
        checkLoginCredentials(userEntity, password);

        return new UsernamePasswordAuthenticationToken(userEntity, null, Collections.singleton(new SimpleGrantedAuthority("User")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    public void checkLoginCredentials(UserEntity userEntity, String providedPassword) {
        if (userEntity == null) {
            throwException(USER_NOT_FOUND);
        }

        if (!passwordEncoder.matches(providedPassword, userEntity.getPassword())) {
            throwException(INCORRECT_DATA);
        }
    }

    public void throwException(String exception) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception);
    }
}
