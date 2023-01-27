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
    private UserEntity deviceOwner;
    @OneToOne
    private UserEntity allowedUser;
    @OneToOne
    private DeviceEntity sharedDevice;
    @Column(name = "sharing_date_from")
    private Date sharingDateFrom;
    @Column(name = "sharing_date_to")
    private Date sharingDateTo;
}
