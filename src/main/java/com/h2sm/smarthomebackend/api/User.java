package com.h2sm.smarthomebackend.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int id;
    private String login;
    private String password;
}
