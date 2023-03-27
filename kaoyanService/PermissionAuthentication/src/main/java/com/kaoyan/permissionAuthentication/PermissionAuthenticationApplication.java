package com.kaoyan.permissionauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.Authentication;

@SpringBootApplication
public class PermissionAuthenticationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PermissionAuthenticationApplication.class, args);
    }

}
