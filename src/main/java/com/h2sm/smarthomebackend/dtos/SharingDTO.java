package com.h2sm.smarthomebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SharingDTO {
    private List<DeviceDTO> sharedByMe;
    private List<DeviceDTO> sharedToMe;
}
