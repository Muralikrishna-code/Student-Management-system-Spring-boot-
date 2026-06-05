package com.murali.studentmanagement.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.murali.studentmanagement.Entity.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long >{
 @EntityGraph(attributePaths = "students")
   List<Department> findAll();
}
