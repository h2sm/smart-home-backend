package com.h2sm.smarthomebackend.api.auth.configuration;

import com.sun.security.auth.UserPrincipal;
import lombok.SneakyThrows;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Component
public class UserHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    @SneakyThrows
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        var uri = request.getURI().getQuery();
        System.out.println(uri);
        final String randomId = UUID.randomUUID().toString();
        System.out.println(randomId);
        //SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(uri, randomId));
        return new UserPrincipal(randomId);
    }

}
