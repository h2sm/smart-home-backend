package com.h2sm.smarthomebackend.controllers;

import com.h2sm.smarthomebackend.dtos.NewHubDTO;
import com.h2sm.smarthomebackend.service.impl.HubServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hub")
@AllArgsConstructor
public class HubController {
    private final HubServiceImpl hubService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addHub(@RequestBody NewHubDTO newHubDTO){
        return ResponseEntity.ok(hubService.addNewHub(newHubDTO));
    }

    @RequestMapping(value = "/{hubUuid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeHub(@PathVariable String hubUuid){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getHubInformation(){
        return null;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseEntity<?> getListOfAvailableHubs(){
        return ResponseEntity.ok(hubService.returnAllAvailableHubsForUser());
    }

}