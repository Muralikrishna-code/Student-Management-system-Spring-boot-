package com.murali.studentmanagement.Exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message)
    {
        super(message);
    }
}
