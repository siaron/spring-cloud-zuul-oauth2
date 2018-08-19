package com.example;

import com.example.config.OAuth2Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xielongwang
 * @create 2018-08-05 下午5:14
 * @email xielong.wang@nvr-china.com
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(value = {OAuth2Properties.class})
public class Oauth2App implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2App.class, args);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
