package com.murali.studentmanagement.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.murali.studentmanagement.Entity.Appuser;
import com.murali.studentmanagement.Service.UserregisterService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/murali")
public class UserregisterController {
 private UserregisterService service;
 public UserregisterController(UserregisterService service)
 {
 this.service=service;
 }
    @PostMapping("/register")
    public Appuser register(@RequestBody Appuser user)
    {
        return service.register(user);
    }
    
    
    

}
