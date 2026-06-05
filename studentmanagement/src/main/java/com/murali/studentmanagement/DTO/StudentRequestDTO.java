package com.murali.studentmanagement.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {

    @NotBlank(message="Name must required")
    private String name;
    @NotBlank(message="course must required")
    private String course;
    @Size(min=10,max=10,message="should have 10 numbers")
    private String phone;
    @Email(message="Invalid format")
    private String email;
    @NotNull(message="deptid required")
   private Long department_id;

}
