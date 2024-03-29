package com.h2sm.smarthomebackend.repository;

import com.h2sm.smarthomebackend.entities.HubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HubRepository extends JpaRepository<HubEntity, Long> {
    HubEntity findHubEntityByHubUuidEquals(String hubUuid);
    List<HubEntity> findAllByHubOwner_UserLoginEquals(String hubOwnerUserLogin);
}