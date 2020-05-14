package com.yonyougov.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@SpringBootApplication
@RestController
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }
    @GetMapping("login")
    public ModelAndView redirect(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("test")
    public String str(String str){
        return str;
    }

    @GetMapping("userinfo")
    public Principal principal(Principal principal){
        return principal;
    }

    @GetMapping("protected")
    public String protectedurl(){
        return "protected";
    }

    //INSERT INTO `menhu`.`oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `load_client_by_client_id`, `scoped`, `secret_required`) VALUES ('login-client', NULL, '{bcrypt}$2a$10$u2IzDWtq.0cSgRNFssWIT.kL.RyAwx3muZwaxTjx459j42u7gcNcy', 'openid,profile,email,resource.read', 'authorization_code,password', 'http://localhost:8081/login/oauth2/code/login-client,http://www.baidu.com,http://localhost:8081/gateway-redirect,http://localhost:8081/login/oauth2/code/aaa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

}
