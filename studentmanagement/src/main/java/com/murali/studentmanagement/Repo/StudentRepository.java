package com.murali.studentmanagement.Repo;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.murali.studentmanagement.Entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
  @EntityGraph(attributePaths = "department")
  Page<Student> findAll(Pageable p);
}
