package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthenticateHubDTO implements Serializable {
    private String hubAuthId;
    private String hubPass;
}
