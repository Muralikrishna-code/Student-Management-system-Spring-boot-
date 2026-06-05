package com.murali.studentmanagement.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.murali.studentmanagement.Service.Customuserservice;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private Customuserservice userdetailservice;
  public SecurityConfig(Customuserservice userdetailservice)
  {
    this.userdetailservice=userdetailservice;
  }
    @Bean
    public SecurityFilterChain securityfliterchain(HttpSecurity http) throws Exception 
    {
        http.csrf(csrf->csrf.disable());
          http.authorizeHttpRequests(auth->auth
                .requestMatchers("/murali/admin/**").hasRole("ADMIN")
                .requestMatchers("/murali/user/**").hasAnyRole("USER","ADMIN")
                .anyRequest()
                .authenticated()
            );
            http.httpBasic(Customizer.withDefaults());
             http.authenticationProvider(authprovider());
            return http.build();
    }
    @Bean
    public PasswordEncoder passwordencoder()
    {
        return new BCryptPasswordEncoder();   
    }
    @Bean
    public DaoAuthenticationProvider authprovider()
    {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userdetailservice);
        provider.setPasswordEncoder(passwordencoder());
        return provider;
    }

}
