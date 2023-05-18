package com.h2sm.smarthomebackend.repository;

import com.h2sm.smarthomebackend.entities.SharedDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharedDeviceRepository extends JpaRepository<SharedDeviceEntity, Long> {
    List<SharedDeviceEntity> findAllByAllowedUser_UsernameEquals(String allowedUserUsername);
    List<SharedDeviceEntity> findSharedDeviceEntitiesByAllowedUser_UserLogin(String allowedUsername);
}
