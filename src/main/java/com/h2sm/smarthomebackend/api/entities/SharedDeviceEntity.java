package com.h2sm.smarthomebackend.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "shared_devices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedDeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "share_id")
    private Long shareId;
    @OneToOne
    @Column(name = "device_owner")
    private UserEntity deviceOwner;
    @OneToOne
    @Column(name = "allowed_user")
    private UserEntity allowedUser;
    @OneToOne
    @Column(name = "shared_device")
    private DeviceEntity sharedDevice;
    @Column(name = "sharing_date_from")
    private Date sharingDateFrom;
    @Column(name = "sharing_date_to")
    private Date sharingDateTo;
}
