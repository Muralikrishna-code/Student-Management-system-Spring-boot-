package com.murali.studentmanagement.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.murali.studentmanagement.Entity.Appuser;
import com.murali.studentmanagement.Repo.UserRepository;

@Service
public class UserregisterService {
    private UserRepository repo;
    private PasswordEncoder passwordencoder;
    public UserregisterService(UserRepository repo,PasswordEncoder passwordencoder )
    {
        this.repo=repo;
        this.passwordencoder=passwordencoder;
    }
public Appuser register(Appuser user)
{  user.setPassword(passwordencoder.encode(user.getPassword()));
    user.setRole("ROLE_USER");
   return repo.save(user);
}
}
