package com.h2sm.smarthomebackend.repository;

import com.h2sm.smarthomebackend.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> getDeviceEntitiesByDeviceOwnerUserLoginEquals(String deviceOwnerLogin);
    @Query(value = "select * from devices", nativeQuery = true)
    List<DeviceEntity> getAllEntities();
    DeviceEntity getDeviceEntityByIdEquals(Long id);
    DeviceEntity getDeviceEntityById(Long id);
    void deleteDeviceEntityByIdEquals(Long id);
}
