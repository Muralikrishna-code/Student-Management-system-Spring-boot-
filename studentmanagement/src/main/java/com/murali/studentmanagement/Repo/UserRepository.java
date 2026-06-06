package com.murali.studentmanagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.murali.studentmanagement.Entity.Appuser;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Appuser,Long> {
  Optional<Appuser> findByUsername(String username);
}
