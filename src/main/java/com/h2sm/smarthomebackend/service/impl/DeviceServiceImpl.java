package com.h2sm.smarthomebackend.service.impl;

import com.h2sm.smarthomebackend.auth.userdetails.UsernameDetails;
import com.h2sm.smarthomebackend.dtos.*;
import com.h2sm.smarthomebackend.entities.DeviceEntity;
import com.h2sm.smarthomebackend.repository.DeviceRepository;
import com.h2sm.smarthomebackend.repository.HubRepository;
import com.h2sm.smarthomebackend.repository.UserRepository;
import com.h2sm.smarthomebackend.service.DeviceService;
import com.h2sm.smarthomebackend.service.entityToDTO.impl.DeviceInformationChanger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private static String TURN_ON = "TURN_ON";
    private static String TURN_OFF = "TURN_OFF";
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final HubRepository hubRepository;
    private final DeviceInformationChanger deviceInformationChanger;
    private final SocketConnectionService socketConnectionService;

    @Transactional
    public boolean addNewDevice(NewDeviceDTO dto) {
        var newDeviceEntity = DeviceEntity.builder()
                .deviceName(dto.getDeviceName())
                .deviceLocation(dto.getDeviceLocation())
                .deviceSerial(dto.getDeviceSerial())
                .localIpAddress(dto.getLocalIpAddress())
                .deviceOwner(userRepository.getUserEntityByUserLogin(UsernameDetails.getUsername()))
                .connectedHub(hubRepository.findHubEntityByHubUuidEquals(dto.getHubUuid()))
                .build();
        deviceRepository.save(newDeviceEntity);
        return true;
    }

    @Override
    @Transactional
    public List<DeviceInformationDTO> returnDevicesList() {
        var listOfDevices = deviceRepository.getDeviceEntitiesByDeviceOwnerUserLoginEquals(UsernameDetails.getUsername());
        var list = listOfDevices.stream().map(deviceInformationChanger::entityToDTO).collect(Collectors.toList());

        return list;
    }

    @Transactional
    public boolean deleteDevice(Long deviceId) {
        deviceRepository.deleteDeviceEntityByIdEquals(deviceId);
        return true;
    }

    @Override
    @Transactional
    public boolean switchDeviceState(Long deviceId, boolean isOn) {
        var deviceEntity = deviceRepository.getDeviceEntityById(deviceId);
        var hubAuthId = deviceEntity.getConnectedHub().getHubUuid();
        var state = isOn ? TURN_ON : TURN_OFF;
        var map = new HashMap<String, String>();
        map.put("ip", deviceEntity.getLocalIpAddress());
        socketConnectionService.sendMessageToHub(hubAuthId, new ActionDTO(state, map));
        return true;
    }

    @Override
    public boolean getDeviceState(Long deviceId) {
        return false;
    }

    @Override
    public DeviceInformationDTO getDeviceInformation(Long deviceId) {
        return null;
    }

    @Override
    public boolean shareDeviceControl(DeviceSharingDTO dto) {
        return false;
    }

    public boolean changeColor(Long deviceId, ChangeColorDTO dto) {
        var deviceEntity = deviceRepository.getDeviceEntityById(deviceId);
        var hubAddress = deviceEntity.getConnectedHub().getHubUuid();
//        var channel = ServerHandler.getChannelsMap().get(hubAddress);
//        channel.writeAndFlush("CHANGE_COLOR:"
//                + deviceEntity.getLocalIpAddress() + ":"
//                + dto.getBrightness() + ":"
//                + dto.getRed() + ":"
//                + dto.getGreen() + ":"
//                + dto.getBlue());
        return true;
    }
}