package com.shirkoubian.coursemanagement.controllers;

import com.shirkoubian.coursemanagement.dtos.CourseDto;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.dtos.StudentDto;
import com.shirkoubian.coursemanagement.services.interfaces.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Response<StudentDto>> createStudent(@Valid @RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<Set<StudentDto>>> getStudents(@RequestParam int pageNumber,
                                                                 @RequestParam int pageSize){
        return new ResponseEntity<>(studentService.getStudents(pageNumber, pageSize), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<StudentDto>> updateStudent(@Valid @RequestBody StudentDto studentDto,
                                                              @PathVariable("id") long studentId){
        return new ResponseEntity<>(studentService.updateStudent(studentDto, studentId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<String>> deleteStudent(@PathVariable("id") long studentId){
        return new ResponseEntity<>(studentService.deleteStudent(studentId), HttpStatus.OK);
    }

    @PostMapping("/addStudentCourse/{studentId}/{courseId}")
    public ResponseEntity<Response<CourseDto>> addStudentCourse(@PathVariable long studentId,
                                                                @PathVariable long courseId){
        return new ResponseEntity<>(studentService.addStudentCourse(studentId, courseId), HttpStatus.OK);
    }

    @GetMapping("/getAverageOfStudent/{id}")
    public ResponseEntity<Response<Object>> getAverageOfStudent(@PathVariable("id") long studentId){
        return new ResponseEntity<>(studentService.getAverageGradesOfStudent(studentId), HttpStatus.OK);
    }
}
