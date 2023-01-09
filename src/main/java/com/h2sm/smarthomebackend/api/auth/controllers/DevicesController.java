package com.h2sm.smarthomebackend.api.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DevicesController {

    @GetMapping("/api/devices/getAll")
    public ResponseEntity<?> getListOfDevices(){
        return ResponseEntity.ok(returnDevice());
    }

    private List returnDevice(){
        var list = new ArrayList<>();
        list.add("Smart Bulb");
        return list;
    }
}
