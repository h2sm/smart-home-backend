package com.h2sm.smarthomebackend.repository;

import com.h2sm.smarthomebackend.entities.SharedDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedDeviceRepository extends JpaRepository<SharedDeviceEntity, Long> {
}
