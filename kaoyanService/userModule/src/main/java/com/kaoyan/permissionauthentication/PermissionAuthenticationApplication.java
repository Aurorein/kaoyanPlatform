package com.kaoyan.permissionauthentication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;



@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PermissionAuthenticationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PermissionAuthenticationApplication.class, args);
    }

}
