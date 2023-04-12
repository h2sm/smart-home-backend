package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticateUserDTO {
    private String email;
    private String password;
    private boolean rememberMe;
}
