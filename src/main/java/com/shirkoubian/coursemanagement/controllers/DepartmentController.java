package com.shirkoubian.coursemanagement.controllers;

import com.shirkoubian.coursemanagement.dtos.DepartmentDto;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.dtos.TeacherDto;
import com.shirkoubian.coursemanagement.services.interfaces.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Response<DepartmentDto>> createDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<DepartmentDto>> updateDepartment(@Valid @RequestBody DepartmentDto departmentDto,
                                                                    @PathVariable("id") long departmentId){
        return new ResponseEntity<>(departmentService.updateDepartment(departmentDto, departmentId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Response<Set<DepartmentDto>>> getDepartments(@RequestParam int pageNumber,
                                                             @RequestParam int pageSize){
        return new ResponseEntity<>(departmentService.getDepartments(pageNumber, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<String>> deleteDepartment(@PathVariable("id") long departmentId){
        return new ResponseEntity<>(departmentService.deleteDepartment(departmentId), HttpStatus.OK);
    }

    @PostMapping("/addGroupManagerToDepartment/{id}")
    public ResponseEntity<Response<String>> addGroupManagerToDepartment(@Valid @RequestBody TeacherDto teacherDto,
                                                                        @PathVariable("id") long departmentId) {
        return new ResponseEntity<>(departmentService.addGroupManagerToDepartment(teacherDto, departmentId),
                HttpStatus.OK);
    }

    @PostMapping("/addTeacherToDepartment/{id}")
    public ResponseEntity<Response<String>> addTeacherToDepartment(@Valid @RequestBody TeacherDto teacherDto,
                                                                   @PathVariable("id") long departmentId){
        return new ResponseEntity<>(departmentService.addTeacherToDepartment(teacherDto, departmentId), HttpStatus.OK);
    }

    @GetMapping("/departmentAverageGrade/{id}")
    public ResponseEntity<Response<Object>> getAverageOfDepartment(@PathVariable("id") long departmentId){
        return new ResponseEntity<>(departmentService.getAverageGradeOfDepartmentStudents(departmentId), HttpStatus.OK);
    }
}
