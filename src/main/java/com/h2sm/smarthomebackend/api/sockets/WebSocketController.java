package com.h2sm.smarthomebackend.api.sockets;

import com.h2sm.smarthomebackend.api.sockets.pojo.Greeting;
import com.h2sm.smarthomebackend.dtos.ActionDTO;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class WebSocketController {
    private SimpUserRegistry simpUserRegistry;

    @MessageMapping("/hello")
    @SendToUser("/queue/greetings")
    public Greeting greeting(@Payload ActionDTO action) {

        return new Greeting("Hello, !");
    }

    @SubscribeMapping("/subscribe")
    public String sendOneTimeMessage() {
        return "server one-time message via the application";
    }

}
