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
    @OneToOne
    @Column(name = "hub_owner")
    private UserEntity hubOwner;
    @Column(name = "global_hub_address")
    private String hubAddress;
    @Column(name = "hub_name")
    private String hubName;
}
