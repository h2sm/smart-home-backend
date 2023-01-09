package com.h2sm.smarthomebackend.api.auth.userdetails;

import com.h2sm.smarthomebackend.api.entities.UserEntity;
import com.h2sm.smarthomebackend.api.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = repository.getUserEntityByUserLogin(email);

        return new User(user.getUserLogin(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("user")));
    }
}
