package com.h2sm.smarthomebackend.service.impl;

import com.h2sm.smarthomebackend.auth.userdetails.UsernameDetails;
import com.h2sm.smarthomebackend.dtos.HubDTO;
import com.h2sm.smarthomebackend.dtos.NewHubDTO;
import com.h2sm.smarthomebackend.entities.HubEntity;
import com.h2sm.smarthomebackend.repository.HubRepository;
import com.h2sm.smarthomebackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HubServiceImpl {
    private final HubRepository hubRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<HubDTO> returnAllAvailableHubsForUser() {
        var hubs = hubRepository.findAllByHubOwner_UserLoginEquals(UsernameDetails.getUsername());
        var availableHubs = new ArrayList<HubDTO>();
        hubs.forEach(hub -> availableHubs.add(new HubDTO(hub.getHubName(), hub.getHubUuid())));
        return availableHubs;
    }

    @Transactional
    public boolean addNewHub(NewHubDTO newHubDTO) {
        var hubEntity = HubEntity.builder()
                .hubOwner(userRepository.getUserEntityByUserLogin(UsernameDetails.getUsername()))
                .hubName(newHubDTO.getHubName())
                .hubUuid(newHubDTO.getHubUuid())
                .hubSecret(newHubDTO.getHubSecret())
                .build();
        hubRepository.save(hubEntity);
        return true;
    }
}