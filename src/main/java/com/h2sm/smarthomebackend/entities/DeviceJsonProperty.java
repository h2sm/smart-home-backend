package com.h2sm.smarthomebackend.entities;

import com.h2sm.smarthomebackend.dtos.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeviceJsonProperty implements Serializable {
    private Map<String, String> values;

    public static DeviceJsonProperty newDevice(DeviceType type) {
        DeviceJsonProperty property = null;
        switch (type) {
            case BULB -> property = new DeviceJsonProperty(Map.of("isOn", "false", "brightness", "100", "r", "255", "g", "255", "b", "255"));
            case CAMERA -> {}
            case SWITCH -> {}
            case THERMOSTAT -> {}
            case VACUUM_CLEANER -> {}
            default -> property = new DeviceJsonProperty();
        }
        return property;
    }
}
