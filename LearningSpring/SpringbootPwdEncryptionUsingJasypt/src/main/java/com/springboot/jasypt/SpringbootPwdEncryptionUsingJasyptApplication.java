package com.springboot.jasypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import lombok.extern.slf4j.Slf4j;

//lombok annotation
@Slf4j
//spring annotation
@SpringBootApplication
//spring jasypt annotation
//helps to make the application understand the encryptable properties
//across the environment
@EnableEncryptableProperties
public class SpringbootPwdEncryptionUsingJasyptApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPwdEncryptionUsingJasyptApplication.class, args);
        log.info("springboot and pwd encryption jasypt application started successfully.");
    }
}
