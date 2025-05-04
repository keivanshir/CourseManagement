package com.shirkoubian.coursemanagement.services.interfaces;

import com.shirkoubian.coursemanagement.dtos.GradeDto;
import com.shirkoubian.coursemanagement.dtos.Response;

import java.util.Set;

public interface GradeService {

    Response<GradeDto> createGrade(GradeDto gradeDto);
    Response<GradeDto> updateGrade(GradeDto gradeDto, long gradeId);
    Response<Set<GradeDto>> getGrades(int pageNumber, int pageSize);
    Response<String> deleteGrade(long gradeId);
}
