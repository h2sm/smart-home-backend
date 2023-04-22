package com.h2sm.smarthomebackend.service.impl;

import com.h2sm.smarthomebackend.auth.userdetails.UsernameDetails;
import com.h2sm.smarthomebackend.dtos.*;
import com.h2sm.smarthomebackend.entities.DeviceEntity;
import com.h2sm.smarthomebackend.entities.HubEntity;
import com.h2sm.smarthomebackend.repository.DeviceRepository;
import com.h2sm.smarthomebackend.repository.HubRepository;
import com.h2sm.smarthomebackend.repository.UserRepository;
import com.h2sm.smarthomebackend.service.DeviceService;
import com.h2sm.smarthomebackend.service.entityToDTO.impl.DeviceInformationChanger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                .deviceType(DeviceType.valueOf(dto.getDeviceType()))
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

        return listOfDevices.stream().map(deviceInformationChanger::entityToDTO).collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteDevice(Long deviceId) {
        var entity = deviceRepository.getDeviceEntityById(deviceId);
        entity.setDeviceOwner(null);
        entity.setConnectedHub(null);
        deviceRepository.save(entity);
        deviceRepository.delete(entity);
//        deviceRepository.deleteDeviceEntityByIdEquals(deviceId);
        return true;
    }

    @Override
    @Transactional
    public boolean switchDeviceState(Long deviceId, boolean isOn) {
        var deviceEntity = deviceRepository.getDeviceEntityById(deviceId);
        deviceEntity.getStatistics().forEach(deviceInfoEntity -> {
            if (deviceInfoEntity.getDeviceEntity() == deviceId && deviceInfoEntity.getKey().equals("isOn"))
                deviceInfoEntity.setValue(Boolean.toString(isOn));
        });
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
        var deviceEntity = deviceRepository.getDeviceEntityById(deviceId);
        return deviceInformationChanger.entityToDTO(deviceEntity);
    }

    @Override
    public boolean shareDeviceControl(DeviceSharingDTO dto) {
        return false;
    }

    public boolean changeColor(Long deviceId, ChangeColorDTO dto) {
        var deviceEntity = deviceRepository.getDeviceEntityById(deviceId);
        var hubUuid = deviceEntity.getConnectedHub().getHubUuid();
        var map = buildColorsMap(dto, deviceEntity.getLocalIpAddress());
        socketConnectionService.sendMessageToHub(hubUuid, ActionDTO.changeColorAction(map));
        deviceEntity.getStatistics().forEach(stats -> {
            if (map.get(stats.getKey()) != null) stats.setValue(map.get(stats.getKey()));
        });
        deviceRepository.save(deviceEntity);
        return true;
    }

    public void updateDataFromDevices() {
        var allDevicesList = deviceRepository.getAllEntities();
        var listOfHubs = allDevicesList.stream().map(DeviceEntity::getConnectedHub).distinct().collect(Collectors.toList());
        listOfHubs.forEach(hub -> {
            var devicesWithThisHub = allDevicesList.stream().filter(deviceEntity -> deviceEntity.getConnectedHub().equals(hub)).collect(Collectors.toList());
            socketConnectionService.sendMessageToHub(hub.getHubUuid(), ActionDTO.updateData(devicesWithThisHub.stream().collect(Collectors.toMap(DeviceEntity::getDeviceName, DeviceEntity::getLocalIpAddress))));
        });

    }

    private Map<String, String> buildColorsMap(ChangeColorDTO dto, String ip) {
        var map = new HashMap<String, String>();
        map.put("brightness", Integer.toString(dto.getBrightness()));
        map.put("r", Integer.toString(dto.getRed()));
        map.put("g", Integer.toString(dto.getGreen()));
        map.put("b", Integer.toString(dto.getBlue()));
        map.put("ip", ip);
        return map;
    }
}