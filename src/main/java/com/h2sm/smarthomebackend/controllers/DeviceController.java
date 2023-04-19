package com.h2sm.smarthomebackend.controllers;

import com.h2sm.smarthomebackend.dtos.DeviceDTO;
import com.h2sm.smarthomebackend.dtos.NewDeviceDTO;
import com.h2sm.smarthomebackend.service.impl.DeviceServiceImpl;
import com.h2sm.smarthomebackend.dtos.ChangeColorDTO;
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
        return ResponseEntity.ok(service.returnDevicesList());
    }

    @RequestMapping(value = "/{deviceId}/state", method = RequestMethod.PUT)
    public ResponseEntity<?> switchDeviceState(@PathVariable String deviceId, @RequestBody boolean isOn) { // switch state of a device (on or off)
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

    @RequestMapping(value = "/{deviceId}/color")
    public ResponseEntity<?> changeColor(@PathVariable Long deviceId, @RequestBody ChangeColorDTO dto) {
        return ResponseEntity.ok(service.changeColor(deviceId, dto));
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> addNewDevice(@RequestBody NewDeviceDTO dto) {
        return ResponseEntity.ok(service.addNewDevice(dto));
    }

    @RequestMapping(value = "/{deviceId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(service.deleteDevice(deviceId));
    }


}