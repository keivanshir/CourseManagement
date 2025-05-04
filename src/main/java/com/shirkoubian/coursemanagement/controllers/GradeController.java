package com.shirkoubian.coursemanagement.controllers;

import com.shirkoubian.coursemanagement.dtos.GradeDto;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.services.interfaces.GradeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/grades")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public ResponseEntity<Response<Set<GradeDto>>> getGrades(@RequestParam int pageNumber,
                                                             @RequestParam int pageSize){
        return new ResponseEntity<>(gradeService.getGrades(pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<GradeDto>> createGrade(@Valid @RequestBody GradeDto gradeDto){
        return new ResponseEntity<>(gradeService.createGrade(gradeDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<GradeDto>> updateGrade(@Valid @RequestBody GradeDto gradeDto,
                                                          @PathVariable("id") long gradeId){
        return new ResponseEntity<>(gradeService.updateGrade(gradeDto, gradeId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<String>> deleteGrade(@PathVariable("id") long gradeId){
        return new ResponseEntity<>(gradeService.deleteGrade(gradeId), HttpStatus.OK);
    }
}
