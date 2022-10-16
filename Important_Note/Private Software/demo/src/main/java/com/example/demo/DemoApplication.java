package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
@Configuration
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role("ROLE_USER", new ArrayList<>()));
            userService.saveRole(new Role("ROLE_MANAGER", new ArrayList<>()));
            userService.saveRole(new Role("ROLE_ADMIN", new ArrayList<>()));
            userService.saveRole(new Role("ROLE_SUPER_ADMIN", new ArrayList<>()));

            userService.saveUser(new User("john","1234","nguyen van a",1, new ArrayList<>()));
            userService.saveUser(new User("will","1234","nguyen van b",1, new ArrayList<>()));
            userService.saveUser(new User("jim","1234","nguyen van c",1, new ArrayList<>()));
            userService.saveUser(new User("arnold","1234","nguyen van d",1, new ArrayList<>()));

            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("john", "ROLE_MANAGER");
            userService.addRoleToUser("will", "ROLE_MANAGER");
            userService.addRoleToUser("jim", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_USER");
            userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
        };
    }

//    @Bean
//    WebMvcConfigurer webMvcConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOriginPatterns("*")
//                        .allowCredentials(true);
//            }
//        };
//    }
}
