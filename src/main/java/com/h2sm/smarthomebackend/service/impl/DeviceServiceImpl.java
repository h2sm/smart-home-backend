package com.h2sm.smarthomebackend.service.impl;

import com.h2sm.smarthomebackend.auth.userdetails.UsernameDetails;
import com.h2sm.smarthomebackend.entities.DeviceEntity;
import com.h2sm.smarthomebackend.repository.DeviceRepository;
import com.h2sm.smarthomebackend.service.DeviceService;
import com.h2sm.smarthomebackend.service.entityToDTO.impl.DeviceInformationChanger;
import com.h2sm.smarthomebackend.dtos.ActionDTO;
import com.h2sm.smarthomebackend.dtos.ChangeColorDTO;
import com.h2sm.smarthomebackend.dtos.DeviceInformationDTO;
import com.h2sm.smarthomebackend.dtos.DeviceSharingDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private static String TURN_ON = "TURN_ON";
    private static String TURN_OFF = "TURN_OFF";
    private final DeviceRepository deviceRepository;
    private final DeviceInformationChanger deviceInformationChanger;
    private final SocketConnectionService socketConnectionService;

    @Override
    @Transactional
    public List<DeviceEntity> returnDevicesList() {
        var listOfDevices = deviceRepository.getDeviceEntitiesByDeviceOwnerUserLoginEquals(UsernameDetails.getUsername());
        listOfDevices.forEach(deviceInformationChanger::entityToDTO);
        return listOfDevices;
    }

    @Override
    @Transactional
    public boolean switchDeviceState(Long deviceId, boolean isOn) {
        var deviceEntity = deviceRepository.getDeviceEntityById(deviceId);
        var hubAuthId = deviceEntity.getConnectedHub().getHubAuthId();
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
        var hubAddress = deviceEntity.getConnectedHub().getHubAuthId();
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