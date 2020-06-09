package com.meipiao.ctrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
public class CtripLandingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CtripLandingApplication.class, args);
    }

}
