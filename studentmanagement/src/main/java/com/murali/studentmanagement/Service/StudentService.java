package com.murali.studentmanagement.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.murali.studentmanagement.DTO.StudentRequestDTO;
import com.murali.studentmanagement.DTO.StudentResponseDTO;
import com.murali.studentmanagement.Entity.Department;
import com.murali.studentmanagement.Entity.Student;
import com.murali.studentmanagement.Exception.DepartmentNotFoundException;
import com.murali.studentmanagement.Exception.StudentNotFoundException;
import com.murali.studentmanagement.Repo.DepartmentRepository;
import com.murali.studentmanagement.Repo.StudentRepository;

@Service
public class StudentService {
    private StudentRepository repo;
    private DepartmentRepository deptrepo;
    public StudentService(StudentRepository repo,DepartmentRepository deptrepo)
    {
        this.repo=repo;
        this.deptrepo=deptrepo;
    }
    public Page<StudentResponseDTO>getallstudents(int page,int size,String sortby,String order) {
           Sort sort;
           if(order.equalsIgnoreCase("asc"))
           {
            sort=Sort.by(sortby).ascending();
           }
           else{
            sort=Sort.by(sortby).descending();
           }
           
        Pageable pageable=PageRequest.of(page,size,sort);
          Page<Student> student = repo.findAll(pageable);
          return student.map(this::maptoResponseDto);       
    }
    public StudentResponseDTO getbyId(Long id) {
        Student st= repo.findById(id).orElseThrow(()->new StudentNotFoundException("student not found with id:"+id));
   
            return maptoResponseDto(st);
    }
    public StudentResponseDTO createstudent(StudentRequestDTO st) {
       Student student=maptoEntity(st);
       repo.save(student);
       return maptoResponseDto(student);

    }
    public StudentResponseDTO updateStudent(Long id,StudentRequestDTO st) {
       Student student=repo.findById(id).orElseThrow(()->new StudentNotFoundException("student not found with id:"+id));
         Department dept=deptrepo.findById(st.getDepartment_id()).orElseThrow(()->new DepartmentNotFoundException("dept not found with this id"));
       student.setName(st.getName());
        student.setCourse(st.getCourse());
        student.setPhone(st.getPhone());
        student.setEmail(st.getEmail());
        student.setDepartment(dept);
        repo.save(student);
        return maptoResponseDto(student);
       }
    public void delete(Long id) {
        Student student=repo.findById(id).orElseThrow(()->new StudentNotFoundException("student not found with id:"+id));
        repo.delete(student);
    }
    /////////
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
    private Student maptoEntity(StudentRequestDTO st)
    { Department dept=deptrepo.findById(st.getDepartment_id()).orElseThrow(()->new DepartmentNotFoundException("dept with this id not found"));

        Student student=new Student();
        student.setName(st.getName());
        student.setCourse(st.getCourse());
        student.setPhone(st.getPhone());
        student.setEmail(st.getEmail());
        student.setDepartment(dept);
        return student;
    }

    

}
