package com.h2sm.smarthomebackend.service.impl;

import com.h2sm.smarthomebackend.dtos.ActionDTO;
import lombok.AllArgsConstructor;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SocketConnectionService {
    private SimpUserRegistry registry;
    private SimpMessagingTemplate messagingTemplate;

    public void sendMessageToHub(String hubAuthId, ActionDTO action) throws MessageDeliveryException {
        if (registry.getUser(hubAuthId) == null ) throw new MessageDeliveryException("Hub " + hubAuthId + " is not connected");
        messagingTemplate.convertAndSendToUser(hubAuthId, "/queue/greetings", action);
    }

    public void parseMessage(ActionDTO actionDTO) {
        System.out.println(actionDTO);
    }
}
