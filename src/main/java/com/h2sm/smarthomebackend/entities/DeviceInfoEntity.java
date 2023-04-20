package com.h2sm.smarthomebackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "device_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceInfoEntity {
    @Id
    @Column(name = "stats_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long statsId;
    @Column(name = "device_id")
    private long deviceEntity;
    @Column(name = "key")
    private String key;
    @Column(name = "value")
    private String value;
}
