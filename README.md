# spring-oauth2-demo


## consul (注册中心)
 - ./consul agent -dev
## 认证服务器 (oauth)
 - oauth
## 资源服务器
 - zuul
 - rental
 - mine
 - iot

## feign oauth2 认证
 - 配置属性使用
 ```
 security:
   oauth2: #为了支持feign配置,可以自定义配置
     client:
       clientId: cloudZuul
       clientSecret: cloudZuulSecret
       accessTokenUri: http://localhost:9003/oauth/token
 ```
 >  不适用oauth2的配置. feign 使用的oauth2的认证方式为:authorization_code ,当请求过来,首先org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor.getToken 检查上下文有没有token,如果没有会去org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor.acquireAccessToken 一个token , 请求微服务

 可以自定义配置属性,example:
  ```
    paascloud:
      oauth2: #为了支持feign配置,可以自定义配置
         client:
           clientId: cloudZuul
           clientSecret: cloudZuulSecret
           accessTokenUri: http://localhost:9003/oauth/token
           ...
  ```
  当自定义属性是需要构造oauth2 认证环境 参考: paascloud
  - 属性类:
  ```
  @Data
  @ConfigurationProperties(prefix = "paascloud.oauth2.client")
  public class Oauth2ClientProperties {
  	private String id;
  	private String accessTokenUrl;
  	private String clientId;
  	private String clientSecret;
  	private String clientAuthenticationScheme;
  }
  ```
  - 配置类:
  ```
  package com.paascloud.security.feign;
  
  import feign.Logger;
  import feign.RequestInterceptor;
  import feign.codec.ErrorDecoder;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Qualifier;
  import org.springframework.boot.context.properties.EnableConfigurationProperties;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.http.client.Netty4ClientHttpRequestFactory;
  import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
  import org.springframework.security.oauth2.client.OAuth2RestTemplate;
  import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
  import org.springframework.security.oauth2.common.AuthenticationScheme;
  
  /**
   *
   * @author https://github.com/paascloud
   */
  @Configuration
  @EnableConfigurationProperties(Oauth2ClientProperties.class)
  public class OAuth2FeignAutoConfiguration {
  
  	private final Oauth2ClientProperties oauth2ClientProperties;
  
  	/**
  	 * Instantiates a new O auth 2 feign auto configuration.
  	 *
  	 * @param oauth2ClientProperties the oauth 2 client properties
  	 */
  	@Autowired
  	public OAuth2FeignAutoConfiguration(Oauth2ClientProperties oauth2ClientProperties) {
  		this.oauth2ClientProperties = oauth2ClientProperties;
  	}
  
  	/**
  	 * Resource details client credentials resource details.
  	 *
  	 * @return the client credentials resource details
  	 */
  	@Bean("paascloudClientCredentialsResourceDetails")
  	public ClientCredentialsResourceDetails resourceDetails() {
  		ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
  		details.setId(oauth2ClientProperties.getId());
  		details.setAccessTokenUri(oauth2ClientProperties.getAccessTokenUrl());
  		details.setClientId(oauth2ClientProperties.getClientId());
  		details.setClientSecret(oauth2ClientProperties.getClientSecret());
  		details.setAuthenticationScheme(AuthenticationScheme.valueOf(oauth2ClientProperties.getClientAuthenticationScheme()));
  		return details;
  	}
  
  	/**
  	 * O auth 2 rest template o auth 2 rest template.
  	 *
  	 * @return the o auth 2 rest template
  	 */
  	@Bean("paascloudOAuth2RestTemplate")
  	public OAuth2RestTemplate oAuth2RestTemplate() {
  		final OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
  		oAuth2RestTemplate.setRequestFactory(new Netty4ClientHttpRequestFactory());
  		return oAuth2RestTemplate;
  
  	}
  
  	/**
  	 * Oauth 2 feign request interceptor request interceptor.
  	 *
  	 * @param oAuth2RestTemplate the o auth 2 rest template
  	 *
  	 * @return the request interceptor
  	 */
  	@Bean
  	public RequestInterceptor oauth2FeignRequestInterceptor(@Qualifier("paascloudOAuth2RestTemplate") OAuth2RestTemplate oAuth2RestTemplate) {
  		return new OAuth2FeignRequestInterceptor(oAuth2RestTemplate);
  	}
  
  	/**
  	 * Feign logger level logger . level.
  	 *
  	 * @return the logger . level
  	 */
  	@Bean
  	Logger.Level feignLoggerLevel() {
  		return Logger.Level.FULL;
  	}
  
  
  	@Bean
  	public ErrorDecoder errorDecoder() {
  		return new Oauth2FeignErrorInterceptor();
  	}
  }
  
  ```
  
 - OAuth2FeignRequestInterceptor
 
 ```
 @Slf4j
 public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
 	private static final String BEARER_TOKEN_TYPE = "bearer";
 
 	private final OAuth2RestTemplate oAuth2RestTemplate;
 
 	/**
 	 * Instantiates a new O auth 2 feign request interceptor.
 	 *
 	 * @param oAuth2RestTemplate the o auth 2 rest template
 	 */
 	OAuth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
 		Assert.notNull(oAuth2RestTemplate, "Context can not be null");
 		this.oAuth2RestTemplate = oAuth2RestTemplate;
 	}
 
 	/**
 	 * Apply.
 	 *
 	 * @param template the template
 	 */
 	@Override
 	public void apply(RequestTemplate template) {
 		log.debug("Constructing Header {} for Token {}", HttpHeaders.AUTHORIZATION, BEARER_TOKEN_TYPE);
 		template.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER_TOKEN_TYPE, oAuth2RestTemplate.getAccessToken().toString()));
 	}
 }
 
 ```
 
 - OAuth2FeignRequestInterceptor
 
 ```
 @Slf4j
 public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
 	private static final String BEARER_TOKEN_TYPE = "bearer";
 
 	private final OAuth2RestTemplate oAuth2RestTemplate;
 
 	/**
 	 * Instantiates a new O auth 2 feign request interceptor.
 	 *
 	 * @param oAuth2RestTemplate the o auth 2 rest template
 	 */
 	OAuth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
 		Assert.notNull(oAuth2RestTemplate, "Context can not be null");
 		this.oAuth2RestTemplate = oAuth2RestTemplate;
 	}
 
 	/**
 	 * Apply.
 	 *
 	 * @param template the template
 	 */
 	@Override
 	public void apply(RequestTemplate template) {
 		log.debug("Constructing Header {} for Token {}", HttpHeaders.AUTHORIZATION, BEARER_TOKEN_TYPE);
 		template.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER_TOKEN_TYPE, oAuth2RestTemplate.getAccessToken().toString()));
 	}
 }

 ```