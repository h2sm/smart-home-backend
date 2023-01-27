package com.h2sm.smarthomebackend.api.repository;

import com.h2sm.smarthomebackend.api.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
}
