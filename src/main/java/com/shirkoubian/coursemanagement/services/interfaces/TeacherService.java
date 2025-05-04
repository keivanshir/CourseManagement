package com.shirkoubian.coursemanagement.services.interfaces;

import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.dtos.TeacherDto;

import java.util.Set;

public interface TeacherService {

    Response<TeacherDto> createTeacher(TeacherDto teacherDto);

    Response<TeacherDto> updateTeacher(TeacherDto teacherDto, long teacherId);

    Response<Set<TeacherDto>> getTeachers(int pageNumber, int pageSize);

    Response<String> deleteTeacher(long teacherId);

    Response<TeacherDto> addTeacherCourse(long teacherId, long courseId);

    Response<Double> getAverageGradeOfTeacherStudentsByEachCourse(long teacherId);

}
