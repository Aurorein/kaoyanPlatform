package com.kaoyan.gateway.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.kaoyan.gateway")
@MapperScan("com.kaoyan.gateway.mapper")
public class GatewayConfig {

}
