package com.murali.studentmanagement.Service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.murali.studentmanagement.Entity.Appuser;
import com.murali.studentmanagement.Repo.UserRepository;

@Service
public class Customuserservice implements UserDetailsService {
  final private UserRepository repo;
  public Customuserservice(UserRepository repo)
  {
    this.repo=repo;
  }
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
       Appuser user= repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));

               return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );

  }

}
