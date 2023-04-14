package com.h2sm.smarthomebackend.sockets.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Greeting implements Serializable {
    @JsonProperty
    private String name;

    public Greeting(String content) {
        this.name = content;
    }

    public String getContent() {
        return name;
    }
}
