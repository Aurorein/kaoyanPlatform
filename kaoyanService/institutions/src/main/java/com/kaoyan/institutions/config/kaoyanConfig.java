package com.kaoyan.institutions.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.kaoyan.institutions")
@MapperScan("com.kaoyan.institutions.mapper")
public class kaoyanConfig {


}
