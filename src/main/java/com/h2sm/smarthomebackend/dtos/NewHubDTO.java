package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewHubDTO implements Serializable {
    private String hubUuid;
    private String hubSecret;
    private String hubName;
}
