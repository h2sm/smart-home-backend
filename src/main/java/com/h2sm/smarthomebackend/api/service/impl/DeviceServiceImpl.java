package com.h2sm.smarthomebackend.api.service.impl;

import com.h2sm.smarthomebackend.api.service.DeviceService;
import com.h2sm.smarthomebackend.dtos.DeviceInformationDTO;
import com.h2sm.smarthomebackend.dtos.DeviceSharingDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    @Override
    public List returnDevicesList() {
        return null;
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
