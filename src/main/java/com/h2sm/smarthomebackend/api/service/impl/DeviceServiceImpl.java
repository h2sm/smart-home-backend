package com.h2sm.smarthomebackend.api.service.impl;

import com.h2sm.smarthomebackend.api.auth.userdetails.UsernameDetails;
import com.h2sm.smarthomebackend.api.repository.DeviceRepository;
import com.h2sm.smarthomebackend.api.service.DeviceService;
import com.h2sm.smarthomebackend.dtos.DeviceInformationDTO;
import com.h2sm.smarthomebackend.dtos.DeviceSharingDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    @Override
    public List returnDevicesList() {
        return deviceRepository.getDeviceEntitiesByDeviceOwnerUserLoginEquals(UsernameDetails.getUsername());
    }

    @Override
    public boolean switchDeviceState(Long deviceId, boolean isOn) {
        return false;
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
}