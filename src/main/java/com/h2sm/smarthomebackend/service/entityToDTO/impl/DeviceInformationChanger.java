package com.h2sm.smarthomebackend.service.entityToDTO.impl;

import com.h2sm.smarthomebackend.entities.DeviceEntity;
import com.h2sm.smarthomebackend.service.entityToDTO.BaseEntityToDTO;
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
                .details(entity.getJsonProperties().getValues())
                .type(entity.getDeviceType())
                .deviceLocation(entity.getDeviceLocation())
                .deviceId(entity.getId())
                .deviceName(entity.getDeviceName())
                .deviceSerial(entity.getDeviceSerial())
                .localDeviceIp(entity.getLocalIpAddress())
                .build();
    }
}
