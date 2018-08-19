package com.common.feign;

import feign.RequestInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

@Configuration
public class FeignClientConfig {

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public OAuth2ProtectedResourceDetails authorizationCodeResourceDetails() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    public RequestInterceptor requestInterceptor(OAuth2ClientContext context, OAuth2ProtectedResourceDetails details) {
        return new OAuth2FeignRequestInterceptor(context, details);
    }
}
