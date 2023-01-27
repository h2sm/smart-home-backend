package com.h2sm.smarthomebackend.api.controllers;

import com.h2sm.smarthomebackend.api.service.impl.DeviceServiceImpl;
import com.h2sm.smarthomebackend.dtos.DeviceSharingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {
    private DeviceServiceImpl service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> getListOfDevices() {
        return ResponseEntity.ok(service.returnDevicesList());
    }


    @RequestMapping(value = "/{deviceId}/state", method = RequestMethod.PUT)
    public ResponseEntity<?> switchDeviceState(@PathVariable Long deviceId, @RequestParam Boolean isOn) { // switch state of a device (on or off)
        return ResponseEntity.ok(service.switchDeviceState(deviceId, isOn));
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