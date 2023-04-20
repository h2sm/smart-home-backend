package com.h2sm.smarthomebackend.entities;

import com.h2sm.smarthomebackend.dtos.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "devices")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceEntity {
    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "device_type")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "device_location")
    private String deviceLocation;
    @Column(name = "device_serial")
    private String deviceSerial;
    @Column(name = "local_ip_address")
    private String localIpAddress;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.ALL
            })
    @JoinTable(name = "device_info",
            joinColumns = {@JoinColumn(name = "device_id"),
            }, inverseJoinColumns = @JoinColumn( name="stats_id"))
    private List<DeviceInfoEntity> statistics;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_owner", referencedColumnName = "user_id")
    private UserEntity deviceOwner;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hub_id", referencedColumnName = "hub_id")
    private HubEntity connectedHub;
}
