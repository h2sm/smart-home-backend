package com.h2sm.smarthomebackend.entities;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_owner", referencedColumnName = "user_id")
    private UserEntity deviceOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "allowed_user", referencedColumnName = "user_id")
    private UserEntity allowedUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shared_device", referencedColumnName = "device_id")
    private DeviceEntity sharedDevice;

    @Column(name = "sharing_date_from")
    private Date sharingDateFrom;

    @Column(name = "sharing_date_to")
    private Date sharingDateTo;
}
