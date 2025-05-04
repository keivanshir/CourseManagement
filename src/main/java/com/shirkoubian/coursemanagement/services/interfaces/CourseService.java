package com.shirkoubian.coursemanagement.services.interfaces;

import com.shirkoubian.coursemanagement.dtos.CourseDto;
import com.shirkoubian.coursemanagement.dtos.Response;

import java.util.Set;

public interface CourseService {

    Response<CourseDto> createCourse(CourseDto courseDto);
    Response<Set<CourseDto>> getCourses(int pageNumber, int pageSize);
    Response<CourseDto> updateCourse(CourseDto courseDto, long courseId);
    Response<String> deleteCourse(long courseId);
}
