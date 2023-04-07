package com.h2sm.smarthomebackend.api.sockets;

import com.h2sm.smarthomebackend.api.sockets.pojo.Greeting;
import com.h2sm.smarthomebackend.api.sockets.pojo.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class HubSocket {
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        System.out.println("Sending Greeting");
//        Thread.sleep(3000); // simulated delay
//        return new Greeting("Hello, " + message.getName() + "!");
//    }
}
