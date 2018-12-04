package com.uhope.messageSent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author ChenBin on 2018/09/04
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableFeignClients(basePackages = {"com.uhope"})
@ComponentScan(basePackages = {"com.uhope"})

public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}