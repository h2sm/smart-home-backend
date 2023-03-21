package com.h2sm.smarthomebackend;

import com.h2sm.smarthomebackend.api.connections.TlsSocketServer;
import com.h2sm.smarthomebackend.netty.NettyServer;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages = "com.h2sm.smarthomebackend.api")
public class SmartHomeBackendApplication {
    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(SmartHomeBackendApplication.class, args);
//        var server = new TlsSocketServer();
//        server.run();
        new NettyServer().run();
    }
}
