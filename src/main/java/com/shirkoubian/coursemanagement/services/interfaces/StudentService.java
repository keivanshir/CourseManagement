package com.shirkoubian.coursemanagement.services.interfaces;

import com.shirkoubian.coursemanagement.dtos.CourseDto;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.dtos.StudentDto;

import java.util.Set;

public interface StudentService {

    Response<StudentDto> createStudent(StudentDto studentDto);

    Response<StudentDto> updateStudent(StudentDto studentDto, long studentId);

    Response<Set<StudentDto>> getStudents(int pageNumber, int pageSize);

    Response<String> deleteStudent(long studentId);

    Response<CourseDto> addStudentCourse(long studentId, long courseId);

    Response<Object> getAverageGradesOfStudent(long studentId);

}
