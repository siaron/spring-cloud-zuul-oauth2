package com.example.security;

import com.common.AuthExceptionEntryPoint;
import com.example.security.authentication.FormAuthenticationConfig;
import com.example.security.authorize.AuthorizeConfigManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author xielong.wang
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAuth2WebSecurityExpressionHandler pcSecurityExpressionHandler;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private AccessDeniedHandler pcAccessDeniedHandler;

    /**
     * Configure.
     *
     * @param http the http
     * @throws Exception the exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //登录
        formAuthenticationConfig.configure(http);

        http.headers().frameOptions().disable()
                .and()
                .exceptionHandling().accessDeniedHandler(pcAccessDeniedHandler)
                .and()
                .csrf()
                .disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.expressionHandler(pcSecurityExpressionHandler)
                .authenticationEntryPoint(new AuthExceptionEntryPoint(new ObjectMapper()));
    }
}
