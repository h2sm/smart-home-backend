package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceInformationDTO {
    private Long deviceId;
    private String deviceName;
    private String deviceLocation;
    private String localDeviceIp;
    private String deviceSerial;
}
