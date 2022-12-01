package com.h2sm.smarthomebackend.api.repository;

import com.h2sm.smarthomebackend.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getUserEntityByUserLogin(String username);
}
