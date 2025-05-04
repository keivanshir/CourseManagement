package com.shirkoubian.coursemanagement.controllers;

import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.dtos.TeacherDto;
import com.shirkoubian.coursemanagement.services.interfaces.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<Response<TeacherDto>> createTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        return new ResponseEntity<>(teacherService.createTeacher(teacherDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<Set<TeacherDto>>> getTeachers(@RequestParam int pageNumber,
                                                                 @RequestParam int pageSize) {
        return new ResponseEntity<>(teacherService.getTeachers(pageNumber, pageSize), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<TeacherDto>> updateTeacher(@Valid @RequestBody TeacherDto teacherDto,
                                                              @PathVariable("id") long teacherId) {
        return new ResponseEntity<>(teacherService.updateTeacher(teacherDto, teacherId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<String>> deleteTeacher(@PathVariable("id") long teacherId) {
        return new ResponseEntity<>(teacherService.deleteTeacher(teacherId), HttpStatus.OK);
    }

    @PostMapping("/addTeacherCourse/{teacherId}/{courseId}")
    public ResponseEntity<Response<TeacherDto>> addTeacherCourse(@PathVariable long teacherId,
                                                                 @PathVariable long courseId) {
        return new ResponseEntity<>(teacherService.addTeacherCourse(teacherId, courseId), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<Double>> getAverageGradeOfTeacherCourse(@PathVariable("id") long teacherId) {
        return new ResponseEntity<>(
                teacherService.getAverageGradeOfTeacherStudentsByEachCourse(teacherId),
                HttpStatus.OK);
    }

}
