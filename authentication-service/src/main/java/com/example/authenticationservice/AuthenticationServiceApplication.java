package com.example.authenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.File;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthenticationServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }

}
