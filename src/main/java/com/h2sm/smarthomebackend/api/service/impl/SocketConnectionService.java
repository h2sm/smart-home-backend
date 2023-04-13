package com.h2sm.smarthomebackend.api.service.impl;

import com.h2sm.smarthomebackend.dtos.ActionDTO;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SocketConnectionService {
    private SimpUserRegistry registry;

    public ActionDTO sendMessageToHub(String hubAuthId){
        return null;
    }
}
