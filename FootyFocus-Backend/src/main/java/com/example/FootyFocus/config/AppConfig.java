package com.example.FootyFocus.config;

import com.example.FootyFocus.repository.UserRepo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    private UserRepo repos;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> repos.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
    @Bean
    public RestTemplate restTemplate(){

        return  new RestTemplate();
    }

}
