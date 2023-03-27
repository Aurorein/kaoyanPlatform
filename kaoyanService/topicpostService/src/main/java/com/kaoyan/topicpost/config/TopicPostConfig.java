package com.kaoyan.topicpost.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.kaoyan.topicpost")
@MapperScan("com.kaoyan.topicpost.mapper")
public class TopicPostConfig {

}
