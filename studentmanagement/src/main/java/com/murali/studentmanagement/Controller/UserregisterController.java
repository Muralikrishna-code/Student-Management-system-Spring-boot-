package com.murali.studentmanagement.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.murali.studentmanagement.Entity.Appuser;
import com.murali.studentmanagement.Jwt.JwtService;
import com.murali.studentmanagement.Service.UserregisterService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/murali")
public class UserregisterController {
 private UserregisterService service;
 private JwtService jwtService;
 private final AuthenticationManager authenticationManager;

 public UserregisterController(UserregisterService service,JwtService jwtService,AuthenticationManager authenticationManager)
 {
 this.service=service;
 this.jwtService=jwtService;
 this.authenticationManager=authenticationManager;
 }
    @PostMapping("/register")
    public Appuser register(@RequestBody Appuser user)
    {
        return service.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody Appuser request) 
    {
         authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
                return jwtService.generateToken(request.getUsername());
    }
}
