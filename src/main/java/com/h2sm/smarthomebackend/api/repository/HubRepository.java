package com.h2sm.smarthomebackend.api.repository;

import com.h2sm.smarthomebackend.api.entities.HubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HubRepository extends JpaRepository<HubEntity, Long> {
}
