package com.murali.studentmanagement.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.murali.studentmanagement.DTO.DepartmentRequestDTO;
import com.murali.studentmanagement.DTO.DepartmentResponsDTO;
import com.murali.studentmanagement.Service.DepartmentService;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/murali")
public class DepartmentController {
    @Autowired
    private  DepartmentService departmentService;

    @GetMapping("/departments")
    public List<DepartmentResponsDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
        @PostMapping("/departments")
    public DepartmentResponsDTO saveDepartment(
           @Valid @RequestBody DepartmentRequestDTO department) {

        return departmentService.saveDepartment(department);
    }
    @DeleteMapping("/departments/{id}")
    public ResponseEntity<String> deleterecord(@PathVariable Long id)
    {               departmentService.deleterecord(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
    @PutMapping("/departments/{id}")
    public ResponseEntity<DepartmentResponsDTO>updaterecord(@PathVariable Long id, @Valid @RequestBody DepartmentRequestDTO dept) {

return new ResponseEntity<>(departmentService.updaterecord(id,dept),HttpStatus.OK);



    }
    

}
