package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionDTO implements Serializable {
    private String action;
    private Map<String, String> map;

    public static ActionDTO changeColorAction(Map<String, String> colorsMap) {
        return new ActionDTO("CHANGE_COLOR", colorsMap);
    }

    public static ActionDTO updateData(Map<String, String> deviceIdToIpMap) {

        return new ActionDTO("GET_STATE_ALL", deviceIdToIpMap);
    }

//    public static ActionDTO updateData(Map<Long, String> deviceIdToIpMap) {
//        //var ints = deviceIdToIpMap.keySet().stream().map(Object::toString).collect(Collectors.toList());
//        //deviceIdToIpMap
//        return new ActionDTO("GET_STATE_ALL", deviceIdToIpMap);
//    }

}
