package com.example;

import com.common.MyRedisTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author xielongwang
 * @create 2018-08-05 下午5:10
 * @email xielong.wang@nvr-china.com
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class IotApp {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    public static void main(String[] args) {
        SpringApplication.run(IotApp.class, args);
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new MyRedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public ResourceServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(jwtTokenStore());
        return tokenServices;
    }

}
