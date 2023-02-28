package com.h2sm.smarthomebackend.api.controllers;

import com.h2sm.smarthomebackend.api.devices.DeviceDTO;
import com.h2sm.smarthomebackend.api.service.impl.DeviceServiceImpl;
import com.h2sm.smarthomebackend.dtos.DeviceSharingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceServiceImpl service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> getListOfDevices() {
        var list = service.returnDevicesList();
        var finalList = new ArrayList<DeviceDTO>();
        list.forEach(device -> {
            var dev = DeviceDTO.builder()
                    .deviceLocation(device.getDeviceLocation())
                    .deviceName(device.getDeviceName())
                    .deviceSerial(device.getDeviceSerial())
                    .id(device.getId())
                    .localIpAddress(device.getLocalIpAddress())
                    .build();
            finalList.add(dev);
        });

        return ResponseEntity.ok(finalList);
    }


    @RequestMapping(value = "/{deviceId}/state", method = RequestMethod.PUT)
    public ResponseEntity<?> switchDeviceState(@PathVariable String deviceId, @RequestBody Boolean isOn) { // switch state of a device (on or off)
        return ResponseEntity.ok(service.switchDeviceState(Long.parseLong(deviceId), isOn));
    }

    @RequestMapping(value = "/{deviceId}/state", method = RequestMethod.GET)
    public ResponseEntity<?> getDeviceState(@PathVariable Long deviceId) { // get state of a device
        return ResponseEntity.ok(service.getDeviceState(deviceId));
    }

    @RequestMapping(value = "/{deviceId}/info", method = RequestMethod.GET)
    public ResponseEntity<?> getInformationAboutDevice(@PathVariable Long deviceId) { // get statistics for a Device
        return ResponseEntity.ok(service.getDeviceInformation(deviceId));
    }

    @RequestMapping(value = "/access", method = RequestMethod.POST)
    public ResponseEntity<?> shareDeviceControl(@RequestBody DeviceSharingDTO dto) {
        return ResponseEntity.ok(service.shareDeviceControl(dto));
    }

}