package com.murali.studentmanagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.murali.studentmanagement.Entity.Appuser;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Appuser,Long> {
  Optional<Appuser> findByUsername(String username);
}
