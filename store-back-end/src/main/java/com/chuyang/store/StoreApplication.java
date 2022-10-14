package com.chuyang.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan(basePackages = "com.chuyang.store.mapper")
@SpringBootApplication
public class StoreApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(StoreApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
