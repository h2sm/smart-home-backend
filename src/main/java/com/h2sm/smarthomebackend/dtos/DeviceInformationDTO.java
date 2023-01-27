package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInformationDTO {
    private Long deviceId;
    private String deviceName;
    private String deviceLocation;
    private String deviceSerial;
    private String localDeviceIp;
}
