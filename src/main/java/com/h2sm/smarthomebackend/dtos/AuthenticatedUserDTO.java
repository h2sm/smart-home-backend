package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthenticatedUserDTO implements Serializable {
    private String token;
    private String username;
}
