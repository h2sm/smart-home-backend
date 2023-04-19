package com.h2sm.smarthomebackend.repository;

import com.h2sm.smarthomebackend.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> getDeviceEntitiesByDeviceOwnerUserLoginEquals(String deviceOwnerLogin);
    DeviceEntity getDeviceEntityByIdEquals(Long id);
    DeviceEntity getDeviceEntityById(Long id);
    void deleteDeviceEntityByIdEquals(Long id);
}
