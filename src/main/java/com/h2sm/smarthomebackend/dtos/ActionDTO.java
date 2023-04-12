package com.h2sm.smarthomebackend.dtos;

import lombok.Data;
import java.io.Serializable;
import java.util.Map;

@Data
public class ActionDTO implements Serializable {
    private String action;
    private Map<String, String> map;
}
