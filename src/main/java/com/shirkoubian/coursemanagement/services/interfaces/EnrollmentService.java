package com.shirkoubian.coursemanagement.services.interfaces;

import com.shirkoubian.coursemanagement.dtos.EnrollmentDto;
import com.shirkoubian.coursemanagement.dtos.Response;

import java.util.Set;

public interface EnrollmentService {

    Response<EnrollmentDto> createEnrollment(long studentId, long courseId);
    Response<EnrollmentDto> updateEnrollment(EnrollmentDto enrollmentDto, long studentId, long courseId);
    Response<Set<EnrollmentDto>> getEnrollments(int pageNumber, int pageSize);
    Response<String> deleteEnrollment(long studentId, long courseId);

}
