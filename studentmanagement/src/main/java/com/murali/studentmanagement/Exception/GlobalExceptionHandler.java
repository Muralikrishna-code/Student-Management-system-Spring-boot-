package com.murali.studentmanagement.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handler(MethodArgumentNotValidException ex)
    {
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult()
          .getFieldErrors()
          .forEach(error->{
            errors.put(error.getField(),error.getDefaultMessage());
    });
    return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> sthandler(StudentNotFoundException ex)
    {     return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> depthandler(DepartmentNotFoundException ex)
    {     return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);

    }
    
    
}
