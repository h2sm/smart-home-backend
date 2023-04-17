package com.h2sm.smarthomebackend.controllers;

import com.h2sm.smarthomebackend.dtos.NewHubDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hub")
@AllArgsConstructor
public class HubController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addHub(@RequestBody NewHubDTO newHubDTO){
        return null;
    }

    @RequestMapping(value = "/{hubUuid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeHub(@PathVariable String hubUuid){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getHubInformation(){
        return null;
    }

}