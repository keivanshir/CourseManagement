package com.shirkoubian.coursemanagement.exception;

import com.shirkoubian.coursemanagement.dtos.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleGlobalException(Exception ex, WebRequest request){

        Response<String> response = Response.<String>builder()
                .message("حطا رخ داده است: " + ex.getMessage() + " در مسیر " + request.getContextPath())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .data("خطا رخ داده است! لطفاً با تیم فنی تماس بگیرید!")
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<String>> handleNotFoundException(NotFoundException ex, WebRequest request){
        Response<String> response = Response.<String>builder()
                .message("حطا رخ داده است: " + ex.getMessage() + " در مسیر " + request.getContextPath())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CalculationException.class)
    public ResponseEntity<Response<String>> handleCalculationException(CalculationException ex, WebRequest request){
        Response<String> response = Response.<String>builder()
                .message("حطا رخ داده است: " + ex.getMessage() + " در مسیر " + request.getContextPath())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentNotEqualException.class)
    public ResponseEntity<Response<String>> handleDepartmentNotEqualException(DepartmentNotEqualException ex, WebRequest request){
        Response<String> response = Response.<String>builder()
                .message("حطا رخ داده است: " + ex.getMessage() + " در مسیر " + request.getContextPath())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeacherNotInDepartmentException.class)
    public ResponseEntity<Response<String>> handleTeacherNotInDepartmentException(TeacherNotInDepartmentException ex,
                                                                                  WebRequest request){
        Response<String> response = Response.<String>builder()
                .message("حطا رخ داده است: " + ex.getMessage() + " در مسیر " + request.getContextPath())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
