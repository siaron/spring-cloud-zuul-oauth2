package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author xielongwang
 * @create 2018-08-05 下午5:12
 * @email xielong.wang@nvr-china.com
 * @description
 */
@SpringBootApplication
@EnableResourceServer
public class MineApp {

    public static void main(String[] args) {
        SpringApplication.run(MineApp.class, args);
    }

}
