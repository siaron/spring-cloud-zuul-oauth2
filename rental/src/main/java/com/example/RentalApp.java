package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author xielongwang
 * @create 2018-08-05 下午5:14
 * @email xielong.wang@nvr-china.com
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {IotFeignClient.class})
public class RentalApp {

    public static void main(String[] args) {
        SpringApplication.run(RentalApp.class, args);
    }
}
