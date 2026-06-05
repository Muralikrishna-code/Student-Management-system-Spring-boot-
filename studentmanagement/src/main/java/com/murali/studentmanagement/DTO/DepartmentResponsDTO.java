package com.murali.studentmanagement.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentResponsDTO {
    private Long id;
    private String deptName;
    private List<StudentResponseDTO> students;
}