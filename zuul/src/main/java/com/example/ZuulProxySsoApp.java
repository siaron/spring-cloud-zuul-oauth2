package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author xielongwang
 * @create 2018-08-05 下午5:15
 * @email xielong.wang@nvr-china.com
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulProxySsoApp {

    public static void main(String[] args) {
        SpringApplication.run(ZuulProxySsoApp.class, args);
    }

}
