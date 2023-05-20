package com.sc.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value = {"com.sc.api.dao.mapper"})
public class SpringSharingUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSharingUserApplication.class, args);
    }

}
