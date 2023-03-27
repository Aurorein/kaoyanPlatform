package com.kaoyan.topicpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TopicpostApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TopicpostApplication.class, args);
    }

}
