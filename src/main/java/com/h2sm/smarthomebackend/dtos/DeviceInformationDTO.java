package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceInformationDTO implements GenericDeviceDTO {
    private DeviceType type;
    private Long deviceId;
    private String deviceName;
    private String deviceLocation;
    private String localDeviceIp;
    private String deviceSerial;
    private Map<String, String> details;
}