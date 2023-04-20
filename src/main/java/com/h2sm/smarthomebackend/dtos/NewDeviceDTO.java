package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewDeviceDTO {
    private String deviceType;
    private String deviceName;
    private String deviceLocation;
    private String deviceSerial;
    private String localIpAddress;
    private String hubUuid;
}