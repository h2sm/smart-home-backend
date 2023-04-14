package com.h2sm.smarthomebackend.service;

import com.h2sm.smarthomebackend.dtos.DeviceInformationDTO;
import com.h2sm.smarthomebackend.dtos.DeviceSharingDTO;

import java.util.List;

public interface DeviceService {
    List returnDevicesList();
    boolean switchDeviceState(Long deviceId, boolean isOn);
    boolean getDeviceState(Long deviceId);
    DeviceInformationDTO getDeviceInformation(Long deviceId);
    boolean shareDeviceControl(DeviceSharingDTO dto);
}
