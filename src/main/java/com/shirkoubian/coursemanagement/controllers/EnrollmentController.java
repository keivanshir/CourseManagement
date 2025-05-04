package com.shirkoubian.coursemanagement.controllers;

import com.shirkoubian.coursemanagement.dtos.EnrollmentDto;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.services.interfaces.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/{studentId}/{courseId}")
    public ResponseEntity<Response<EnrollmentDto>> createEnrollment(@PathVariable long studentId,
                                                                    @PathVariable long courseId){
        return new ResponseEntity<>(enrollmentService.createEnrollment(studentId, courseId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<Set<EnrollmentDto>>> getEnrollments(@RequestParam int pageNumber,
                                                                       @RequestParam int pageSize){
        return new ResponseEntity<>(enrollmentService.getEnrollments(pageNumber, pageSize), HttpStatus.OK);
    }

    @PutMapping("/{studentId}/{courseId}")
    public ResponseEntity<Response<EnrollmentDto>> updateEnrollment(@Valid @RequestBody EnrollmentDto enrollmentDto,
                                                                    @PathVariable long studentId,
                                                                    @PathVariable long courseId){
        return new ResponseEntity<>(enrollmentService.updateEnrollment(enrollmentDto, studentId, courseId),
                HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}/{courseId}")
    public ResponseEntity<Response<String>> deleteEnrollment(@PathVariable long studentId,
                                                             @PathVariable long courseId){
        return new ResponseEntity<>(enrollmentService.deleteEnrollment(studentId, courseId), HttpStatus.OK);
    }
}
