package com.h2sm.smarthomebackend.repository;

import com.h2sm.smarthomebackend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getUserEntityByUserLogin(String username);

}
