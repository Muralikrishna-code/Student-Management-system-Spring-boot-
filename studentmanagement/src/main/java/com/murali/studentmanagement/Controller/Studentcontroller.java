package com.murali.studentmanagement.Controller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.murali.studentmanagement.DTO.StudentRequestDTO;
import com.murali.studentmanagement.DTO.StudentResponseDTO;
import com.murali.studentmanagement.Service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/murali")
public class Studentcontroller {
    private final StudentService service;
    public Studentcontroller(StudentService service)
{
    this.service=service;
}
@GetMapping({"/user/students","/admin/students"})
public ResponseEntity<Page<StudentResponseDTO>> getallstudents(@RequestParam(required=false,defaultValue="0") int page,@RequestParam(required=false,defaultValue="2") int size,@RequestParam(required=false,defaultValue = "stdid") String sortby,@RequestParam(required=false,defaultValue = "asc") String order)
{    return new ResponseEntity<>(service.getallstudents(page,size,sortby,order),HttpStatus.OK);
}   
@GetMapping({"/user/students/{id}","/admin/students/{id}"})
public ResponseEntity<StudentResponseDTO> getbyId(@PathVariable Long id)
{
    return new ResponseEntity<>(service.getbyId(id),HttpStatus.OK);
}
@PostMapping("/admin/students")
public ResponseEntity<StudentResponseDTO> createstudent(@Valid @RequestBody StudentRequestDTO st)
{   
     return new ResponseEntity<>(service.createstudent(st),HttpStatus.CREATED);
}
@PutMapping("/admin/students/{id}")
public ResponseEntity<StudentResponseDTO> updatest(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO st)
{
    return new ResponseEntity<>(service.updateStudent(id,st),HttpStatus.OK);
}
@DeleteMapping("/admin/students/{id}")
public ResponseEntity<String> delete(@PathVariable Long id)
{   service.delete(id);
      return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);

}


    
}
