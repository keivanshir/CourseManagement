package com.shirkoubian.coursemanagement.controllers;

import com.shirkoubian.coursemanagement.dtos.CourseDto;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.services.interfaces.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Response<Set<CourseDto>>> getCourses(@RequestParam("pageNumber") int pageNumber,
                                                               @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(courseService.getCourses(pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<CourseDto>> createCourse(@Valid @RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.createCourse(courseDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<CourseDto>> updateCourse(@Valid @RequestBody CourseDto courseDto,
                                                            @PathVariable("id") long courseId) {
        return new ResponseEntity<>(courseService.updateCourse(courseDto, courseId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<String>> deleteCourse(@PathVariable("id") long courseId) {
        return new ResponseEntity<>(courseService.deleteCourse(courseId), HttpStatus.OK);
    }
}
