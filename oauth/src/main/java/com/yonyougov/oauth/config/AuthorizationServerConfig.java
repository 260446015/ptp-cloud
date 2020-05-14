package com.yonyougov.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/3/19
 * 授权认证服务中心
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    UserDetailsService userDetailsService;
    @Resource
    private DataSource dataSource;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    RedisConnectionFactory redisConnectionFactory;
    //配置appid、appkey、回调地址、token有效期
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("login-client").secret(passwordEncoder.encode("secret"))
//                .redirectUris("http://localhost:8081/login/oauth2/code/login-client","http://localhost:8086/sso/login","http://localhost:8086/sso/login/oauth2/code/login-client")
//                .scopes("all").authorizedGrantTypes("authorization_code","refresh_token","password")
//                .authorities("uaa.resource")
//                .accessTokenValiditySeconds(6000).refreshTokenValiditySeconds(60000)
//                .and()
//        .withClient("client_1").secret(passwordEncoder.encode("123456"))
//                .redirectUris("http://www.baidu.com").authorizedGrantTypes("authorization_code","refresh_token","password")
//                .scopes("all").accessTokenValiditySeconds(7200).refreshTokenValiditySeconds(7200);
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("permitAll()");
    }



}
