package com.h2sm.smarthomebackend.service.impl;

import com.h2sm.smarthomebackend.auth.userdetails.UsernameDetails;
import com.h2sm.smarthomebackend.dtos.*;
import com.h2sm.smarthomebackend.entities.DeviceEntity;
import com.h2sm.smarthomebackend.entities.DeviceJsonProperty;
import com.h2sm.smarthomebackend.repository.DeviceRepository;
import com.h2sm.smarthomebackend.repository.HubRepository;
import com.h2sm.smarthomebackend.repository.UserRepository;
import com.h2sm.smarthomebackend.service.DeviceService;
import com.h2sm.smarthomebackend.service.entityToDTO.impl.DeviceInformationChanger;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
                .jsonProperties(DeviceJsonProperty.newDevice(DeviceType.valueOf(dto.getDeviceType())))
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
        deviceRepository.deleteAllByIdInBatch(Set.of(deviceId));
//        var entity = deviceRepository.getDeviceEntityById(deviceId);
//        entity.setDeviceOwner(null);
//        entity.setConnectedHub(null);
//        deviceRepository.save(entity);
//        deviceRepository.delete(entity);
//        deviceRepository.deleteDeviceEntityByIdEquals(deviceId);
        return true;
    }

    @Override
    @Transactional
    public boolean switchDeviceState(Long deviceId, boolean isOn) {
        var deviceEntity = deviceRepository.getDeviceEntityById(deviceId);
        getDeviceProperties(deviceEntity).put("isOn", Boolean.toString(isOn));
        deviceRepository.save(deviceEntity);
        var hubAuthId = deviceEntity.getConnectedHub().getHubUuid();
        var state = isOn ? TURN_ON : TURN_OFF;
        var map = new HashMap<String, String>();
        map.put("ip", deviceEntity.getLocalIpAddress());
        //socketConnectionService.sendMessageToHub(hubAuthId, new ActionDTO(state, map));
        return true;
    }

    private Map<String, String> getDeviceProperties(DeviceEntity entity){
        return entity.getJsonProperties().getValues();
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

    @Transactional
    public boolean changeColor(Long deviceId, ChangeColorDTO dto) {
        var deviceEntity = deviceRepository.getDeviceEntityById(deviceId);
        setColorsToJSON(getDeviceProperties(deviceEntity), dto);
        deviceRepository.save(deviceEntity);

        var hubUuid = deviceEntity.getConnectedHub().getHubUuid();
        var map = buildColorsMap(dto, deviceEntity.getLocalIpAddress());
        socketConnectionService.sendMessageToHub(hubUuid, ActionDTO.changeColorAction(map));
        return true;
    }

    private void setColorsToJSON(Map<String, String> map, ChangeColorDTO dto){
        map.put("r", String.valueOf(dto.getRed()));
        map.put("g", String.valueOf(dto.getGreen()));
        map.put("b", String.valueOf(dto.getBlue()));
        map.put("brightness", String.valueOf(dto.getBrightness()));
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