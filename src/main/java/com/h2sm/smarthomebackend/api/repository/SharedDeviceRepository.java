package com.h2sm.smarthomebackend.api.repository;

import com.h2sm.smarthomebackend.api.entities.SharedDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedDeviceRepository extends JpaRepository<SharedDeviceEntity, Long> {
}
