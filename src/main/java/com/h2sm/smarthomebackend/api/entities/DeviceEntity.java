package com.h2sm.smarthomebackend.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "devices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceEntity {
    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "device_location")
    private String deviceLocation;
    @Column(name = "device_serial")
    private String deviceSerial;
    @Column(name = "local_ip_address")
    private String localIpAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_owner", referencedColumnName = "user_id")
    private UserEntity deviceOwner;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hub_id", referencedColumnName = "hub_id")
    private HubEntity connectedHub;

}
