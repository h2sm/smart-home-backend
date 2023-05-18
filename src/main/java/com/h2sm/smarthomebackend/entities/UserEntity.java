package com.h2sm.smarthomebackend.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "user_surname")
    private String userSurname;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "user_password")
    private String password;

}
