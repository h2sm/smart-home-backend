package com.h2sm.smarthomebackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "hubs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hub_id")
    private Long hubId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hub_owner", referencedColumnName = "user_id")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private UserEntity hubOwner;
    @Column(name = "hub_name")
    private String hubName;
    @Column(name = "hub_uuid")
    private String hubUuid;
    @Column(name = "hub_secret")
    private String hubSecret;
}
