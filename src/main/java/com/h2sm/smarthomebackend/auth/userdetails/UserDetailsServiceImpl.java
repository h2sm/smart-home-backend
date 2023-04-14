package com.h2sm.smarthomebackend.auth.userdetails;

import com.h2sm.smarthomebackend.entities.UserEntity;
import com.h2sm.smarthomebackend.repository.AuthRepository;
import com.h2sm.smarthomebackend.repository.HubRepository;
import com.h2sm.smarthomebackend.repository.UserRepository;
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
    private final UserRepository repository;
    private final HubRepository hubRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = repository.getUserEntityByUserLogin(email);

        return new User(user.getUserLogin(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("user")));
    }

    @Transactional
    public UserDetails loadByHub(String hubId) {
        var hub = hubRepository.findHubEntityByHubAuthIdEquals(hubId);
        return new User(hub.getHubAuthId(), hub.getHubName(), Collections.singleton(new SimpleGrantedAuthority("user")));

    }
}
