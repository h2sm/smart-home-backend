package com.h2sm.smarthomebackend.api.auth.provider;

import com.h2sm.smarthomebackend.api.auth.configuration.JWTUtils;
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
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    @Override
    public UsernamePasswordAuthenticationToken authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        var userEntity = authRepository.getUserEntityByUserLogin(email);
        checkLoginCredentials(userEntity, password);
        var jwt = jwtUtils.createJWT(userEntity);

        return new UsernamePasswordAuthenticationToken(userEntity, jwt, Collections.singleton(new SimpleGrantedAuthority("User")));
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
