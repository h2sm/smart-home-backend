package com.h2sm.smarthomebackend.auth.userdetails;

import com.h2sm.smarthomebackend.entities.UserEntity;
import com.h2sm.smarthomebackend.repository.AuthRepository;
import com.h2sm.smarthomebackend.repository.HubRepository;
import com.h2sm.smarthomebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final HubRepository hubRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = repository.getUserEntityByUserLogin(email);

        return new User(user.getUserLogin(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("user")));
    }

    @Transactional
    public UserDetails loadByHub(String hubUuid) {
        var hub = hubRepository.findHubEntityByHubUuidEquals(hubUuid);
        return new User(hub.getHubUuid(), hub.getHubSecret(), Collections.singleton(new SimpleGrantedAuthority("user")));

    }
}
