package com.shirkoubian.coursemanagement.exception;

public class TeacherNotInDepartmentException extends RuntimeException{
    public TeacherNotInDepartmentException(String message) {
        super(message);
    }
}
