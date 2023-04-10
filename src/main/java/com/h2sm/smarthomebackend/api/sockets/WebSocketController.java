package com.h2sm.smarthomebackend.api.sockets;

import com.h2sm.smarthomebackend.api.sockets.pojo.Greeting;
import com.h2sm.smarthomebackend.api.sockets.pojo.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendToUser("/queue/greetings")
    public Greeting greeting(@Payload HelloMessage message) throws Exception {
        System.out.println("ye " + message.getName());
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @SubscribeMapping("/subscribe")
    public String sendOneTimeMessage() {
        return "server one-time message via the application";
    }

}
