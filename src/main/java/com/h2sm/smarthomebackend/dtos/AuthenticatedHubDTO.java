package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthenticatedHubDTO implements Serializable {
    private String token;
}
