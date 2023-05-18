package com.h2sm.smarthomebackend.auth.configuration;

import com.sun.security.auth.UserPrincipal;
import lombok.SneakyThrows;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Component
public class UserHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    @SneakyThrows
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        var queryParams = UriComponentsBuilder.fromUriString(request.getURI().toString())
                                                                        .build().getQueryParams();
        var hubAuthId = queryParams.get("hubUuid").get(0);
        request.getHeaders().entrySet();
        for (Map.Entry<String, List<String>> entry : request.getHeaders().entrySet()) {
            var val = entry.getValue();
            System.out.println(entry.getKey() + ":" + val);
        }
        return new UserPrincipal(hubAuthId);
    }

}
