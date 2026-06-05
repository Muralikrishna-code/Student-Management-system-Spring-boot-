package com.murali.studentmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {
    
    private Long stdid;
    private String name;
    private String course;
    private String phone;
    private String deptname;
}
