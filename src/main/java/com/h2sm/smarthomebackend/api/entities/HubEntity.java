package com.h2sm.smarthomebackend.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "hubs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hub_id")
    private Long hubId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hub_owner", referencedColumnName = "user_id")
    private UserEntity hubOwner;
    @Column(name = "global_hub_address")
    private String hubAddress;
    @Column(name = "hub_name")
    private String hubName;
    @Column(name = "hub_auth_id")
    private String hubAuthId;
}
