package com.h2sm.smarthomebackend.dtos;

import com.h2sm.smarthomebackend.entities.DeviceEntity;
import com.h2sm.smarthomebackend.entities.HubEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionDTO implements Serializable {
    private String action;
    private Map<String, String> map;

    public static ActionDTO changeColorAction(Map<String, String> colorsMap) {
        return new ActionDTO("CHANGE_COLOR", colorsMap);
    }

    public static ActionDTO updateData(Map<String,String> namesToIpsMap) {

        return new ActionDTO("GET_STATE_ALL", namesToIpsMap);
    }

}
