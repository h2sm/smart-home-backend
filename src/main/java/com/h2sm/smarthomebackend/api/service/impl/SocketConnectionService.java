package com.h2sm.smarthomebackend.api.service.impl;

import com.h2sm.smarthomebackend.dtos.ActionDTO;
import lombok.AllArgsConstructor;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SocketConnectionService {
    private SimpUserRegistry registry;
    private SimpMessagingTemplate messagingTemplate;

    public void sendMessageToHub(String hubAuthId, ActionDTO action) {
        try {
            messagingTemplate.convertAndSendToUser(hubAuthId, "/queue/greetings", action);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void parseMessage(ActionDTO actionDTO) {
        System.out.println(actionDTO);
    }
}
