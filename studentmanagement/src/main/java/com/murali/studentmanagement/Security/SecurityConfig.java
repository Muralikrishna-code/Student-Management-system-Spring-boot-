package com.murali.studentmanagement.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityfliterchain(HttpSecurity http) throws Exception 
    {
        http.csrf(csrf->csrf.disable());
          http.authorizeHttpRequests(auth->auth
                .requestMatchers("/murali/students/*","/murali/departments/*")
                .authenticated()
                .anyRequest().permitAll()
            );
            http.formLogin();
            
           
            return http.build();
    }
}
