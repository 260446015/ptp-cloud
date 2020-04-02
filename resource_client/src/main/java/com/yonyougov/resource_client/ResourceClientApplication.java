package com.yonyougov.resource_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@RestController
@RequestMapping("test")
public class ResourceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceClientApplication.class, args);
    }

    @GetMapping
    public ResponseEntity str(String str){
        return ResponseEntity.ok(str);
    }

}
