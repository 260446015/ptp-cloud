package com.yonyougov.oauth.config;

import com.yonyougov.oauth.entity.Users;
import com.yonyougov.oauth.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/29
 */
@Component
public class RemoteUserCenterAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    public RemoteUserCenterAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println(authentication);
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Users users = userRepository.findByUsernameAndPassword(username,password);
        if(users != null){
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
