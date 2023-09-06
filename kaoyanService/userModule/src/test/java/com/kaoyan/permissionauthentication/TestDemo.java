package com.kaoyan.permissionauthentication;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
public class TestDemo {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void TestBCryptPasswordEncoder(){

//        $2a$10$npv5JSeFR6/wLz8BBMmSBOMb8byg2eyfK4/vvoBk3RKtTLBhIhcpy

        System.out.println("=========matchResult:"+passwordEncoder.
                matches("12345",
                        "$2a$10$r7YSykbHtsWFrawG5owiCeyjJDdE0mzOZHmLYh0SiIxUCsPM02xa2"));
        String encode = passwordEncoder.encode("12345");
//        String encode2 = passwordEncoder.encode("1234");
        System.out.println(encode);
//        System.out.println(encode2);

    }



}
