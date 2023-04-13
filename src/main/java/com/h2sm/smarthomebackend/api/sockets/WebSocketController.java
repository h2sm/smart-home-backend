package com.h2sm.smarthomebackend.api.sockets;

import com.h2sm.smarthomebackend.api.service.impl.SocketConnectionService;
import com.h2sm.smarthomebackend.api.sockets.pojo.Greeting;
import com.h2sm.smarthomebackend.dtos.ActionDTO;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
@AllArgsConstructor
public class WebSocketController {
    private SimpUserRegistry simpUserRegistry;
    private SocketConnectionService service;

    @MessageMapping("/hello")
    @SendToUser("/queue/greetings")
    public ActionDTO greeting(@Payload ActionDTO action) {
        simpUserRegistry.getUsers().forEach(System.out::println);
        return new ActionDTO("Hello", new HashMap<>());
    }
    @MessageMapping("/hello/resp")
    public void responseFromHub(@Payload ActionDTO actionDTO){
        service.parseMessage(actionDTO);
    }

    @SubscribeMapping("/subscribe")
    public String sendOneTimeMessage() {
        return "server one-time message via the application";
    }

}
