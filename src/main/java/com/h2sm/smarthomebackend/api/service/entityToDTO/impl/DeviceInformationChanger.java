package com.h2sm.smarthomebackend.api.service.entityToDTO.impl;

import com.h2sm.smarthomebackend.api.entities.DeviceEntity;
import com.h2sm.smarthomebackend.api.service.entityToDTO.BaseEntityToDTO;
import com.h2sm.smarthomebackend.dtos.DeviceInformationDTO;
import org.springframework.stereotype.Component;

@Component
public class DeviceInformationChanger implements BaseEntityToDTO<DeviceEntity, DeviceInformationDTO> {
    @Override
    public DeviceEntity dtoToEntity(DeviceInformationDTO dto) {
        return null;
    }

    @Override
    public DeviceInformationDTO entityToDTO(DeviceEntity entity) {
        return DeviceInformationDTO.builder()
                .deviceLocation(entity.getDeviceLocation())
                .deviceId(entity.getId())
                .deviceName(entity.getDeviceName())
                .deviceSerial(entity.getDeviceSerial())
                .localDeviceIp(entity.getLocalIpAddress())
                .build();
    }
}
