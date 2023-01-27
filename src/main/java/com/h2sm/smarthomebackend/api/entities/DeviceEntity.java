package com.h2sm.smarthomebackend.api.entities;

import javax.persistence.*;

@Entity
public class DeviceEntity {
    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private String deviceName;
}
