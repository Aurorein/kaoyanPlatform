package com.kaoyan.permissionauthentication.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.kaoyan.permissionauthentication")
@MapperScan("com.kaoyan.permissionauthentication.mapper")
public class PAConfig {

}
