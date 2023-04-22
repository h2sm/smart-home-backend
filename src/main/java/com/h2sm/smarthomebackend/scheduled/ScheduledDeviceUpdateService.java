package com.h2sm.smarthomebackend.scheduled;

import com.h2sm.smarthomebackend.service.impl.DeviceServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class ScheduledDeviceUpdateService {
    private final DeviceServiceImpl deviceService;

//    @Scheduled(cron = "*/2 * * * *")
//    private void updateDevicesState(){
//        log.debug("Fetching updates from devices");
//        deviceService.updateDataFromDevices();
//        log.debug("Fetching data completed");
//    }
}
