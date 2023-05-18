package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceSharingDTO {
    private Long sharedDeviceId;
    private String allowedUserLogin;
    private Date dateFrom;
    private Date dateTo;
}
