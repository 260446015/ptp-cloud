package com.yonyougov.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/3/19
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private DataSource dataSource;
    @Resource
    private TigerAuthenticationSuccessHandler tigerAuthenticationSuccessHandler;
    @Resource
    private TigerAuthenticationFailureHandler tigerAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests().antMatchers("/**").fullyAuthenticated()
                .antMatchers("/login","/authentication/require").permitAll()
                .and().httpBasic()
                .and().formLogin().loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form").successHandler(tigerAuthenticationSuccessHandler).failureHandler(tigerAuthenticationFailureHandler)
                .permitAll()
        ;
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//        String finalPassword = "{bcrypt}"+bCryptPasswordEncoder.encode("123456");
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user_1").password(finalPassword).authorities("USER").build());
//        manager.createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
//
//        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }



}
