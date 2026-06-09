package com.murali.studentmanagement.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.murali.studentmanagement.Jwt.JwtFilter;
import com.murali.studentmanagement.Service.Customuserservice;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private Customuserservice userdetailservice;
      private final JwtFilter jwtFilter;
  public SecurityConfig(Customuserservice userdetailservice,JwtFilter jwtFilter)
  {
    this.userdetailservice=userdetailservice;
      this.jwtFilter=jwtFilter;
  }
    @Bean
    public SecurityFilterChain securityfliterchain(HttpSecurity http) throws Exception 
    {
        http.csrf(csrf->csrf.disable());
                http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
          http.authorizeHttpRequests(auth->auth
                .requestMatchers("/murali/login","/murali/register").permitAll()
                .requestMatchers("/murali/admin/**").hasRole("ADMIN")
                .requestMatchers("/murali/user/**").hasAnyRole("USER","ADMIN")
                .anyRequest()
                .authenticated()
            );
            http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
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
    @Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
}

}
