package com.murali.studentmanagement.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.murali.studentmanagement.DTO.DepartmentRequestDTO;
import com.murali.studentmanagement.DTO.DepartmentResponsDTO;
import com.murali.studentmanagement.DTO.StudentRequestDTO;
import com.murali.studentmanagement.DTO.StudentResponseDTO;
import com.murali.studentmanagement.Entity.Department;
import com.murali.studentmanagement.Entity.Student;
import com.murali.studentmanagement.Exception.DepartmentNotFoundException;
import com.murali.studentmanagement.Repo.DepartmentRepository;


@Service
public class DepartmentService {
    @Autowired
  private DepartmentRepository deptrepp;
   @Transactional
  public List<DepartmentResponsDTO> getAllDepartments() {
    List<Department> departments=deptrepp.findAll();
    List<DepartmentResponsDTO> result=departments.stream().map(this::maptoResponsedto).collect(Collectors.toList());
    return result;
}
 public DepartmentResponsDTO saveDepartment(DepartmentRequestDTO department) {

         Department dept=maptoentity(department);
         dept.getStudents().forEach(st->st.setDepartment(dept));
         deptrepp.save(dept);
         return maptoResponsedto(dept); 
    }
     public void deleterecord(Long id) {
        Department dept=deptrepp.findById(id).orElseThrow(()->new DepartmentNotFoundException("dept with id not found:"+id));
        deptrepp.delete(dept);
    }
public DepartmentResponsDTO updaterecord(Long id, DepartmentRequestDTO dept) {
 Department department=deptrepp.findById(id).orElseThrow(()->new DepartmentNotFoundException("dept with id not found:"+id));
     department.setDeptname(dept.getDeptName());
     List<Student> stud=dept.getStudents().stream().map(this::maptoEntity).collect(Collectors.toList());
     stud.forEach(st->st.setDepartment(department));
     department.setStudents(stud);
     deptrepp.save(department);
     return maptoResponsedto(department);
}

    /////////////////////DTOs
private StudentResponseDTO maptoResponseDto(Student st)
    {  Department dept=st.getDepartment();
        StudentResponseDTO stdres=new StudentResponseDTO();
        stdres.setStdid(st.getStdid());
        stdres.setName(st.getName());
        stdres.setCourse(st.getCourse());
        stdres.setPhone(st.getPhone());
        stdres.setDeptname(dept.getDeptname());
        return stdres;
    }
    public DepartmentResponsDTO maptoResponsedto(Department dept)
    {    DepartmentResponsDTO dto=new DepartmentResponsDTO();
        dto.setDeptName(dept.getDeptname());
        dto.setId(dept.getDepartment_id());
        //n+1 problem occured
        List<StudentResponseDTO> srd=dept.getStudents().stream().map(this::maptoResponseDto).collect(Collectors.toList());
        dto.setStudents(srd);
        return dto;
    }
    private Student maptoEntity(StudentRequestDTO st)
    { 
        Student student=new Student();
        student.setName(st.getName());
        student.setCourse(st.getCourse());
        student.setPhone(st.getPhone());
        student.setEmail(st.getEmail());
        return student;
    }
    public Department maptoentity(DepartmentRequestDTO departement)
    {
        Department dept=new Department();
        dept.setDeptname(departement.getDeptName());
        List<Student> students=departement.getStudents().stream().map(this::maptoEntity).collect(Collectors.toList());
        dept.setStudents(students);
        return dept;
    }
    

}