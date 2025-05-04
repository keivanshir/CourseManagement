package com.shirkoubian.coursemanagement.services.interfaces;

import com.shirkoubian.coursemanagement.dtos.DepartmentDto;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.dtos.TeacherDto;

import java.util.Set;

public interface DepartmentService {
    Response<DepartmentDto> createDepartment(DepartmentDto departmentDto);
    Response<Set<DepartmentDto>> getDepartments(int pageNumber, int pageSize);
    Response<DepartmentDto> updateDepartment(DepartmentDto departmentDto, long departmentId);
    Response<String> deleteDepartment(long departmentId);
    Response<String> addGroupManagerToDepartment(TeacherDto teacherDto, long departmentId);
    Response<String> addTeacherToDepartment(TeacherDto teacherDto, long departmentId);
    Response<Object> getAverageGradeOfDepartmentStudents(long departmentId);


}
