package com.shirkoubian.coursemanagement.services.implementations;

import com.shirkoubian.coursemanagement.dtos.EnrollmentDto;
import com.shirkoubian.coursemanagement.dtos.EntityMapper;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.entities.Course;
import com.shirkoubian.coursemanagement.entities.Enrollment;
import com.shirkoubian.coursemanagement.entities.EnrollmentId;
import com.shirkoubian.coursemanagement.entities.Student;
import com.shirkoubian.coursemanagement.exception.NotFoundException;
import com.shirkoubian.coursemanagement.repositories.CourseRepository;
import com.shirkoubian.coursemanagement.repositories.EnrollmentRepository;
import com.shirkoubian.coursemanagement.repositories.StudentRepository;
import com.shirkoubian.coursemanagement.services.interfaces.EnrollmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Response<EnrollmentDto> createEnrollment(long studentId, long courseId) {

            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new NotFoundException("دانشجو با شناسه " + studentId + " پیدا نشد!"));
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new NotFoundException("درس با شناسه " + courseId + " پیدا نشد!"));

            Enrollment enrollment = new Enrollment();
            enrollment.setCourse(course);
            enrollment.setStudent(student);

            Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

            return Response.<EnrollmentDto>builder()
                    .message("درس برای دانشجو ثبت شد!")
                    .statusCode(HttpStatus.OK.value())
                    .data(EntityMapper.toEnrollmentDto(savedEnrollment))
                    .build();

    }

    @Override
    public Response<EnrollmentDto> updateEnrollment(EnrollmentDto enrollmentDto, long studentId, long courseId) {

        return null;
    }

    @Override
    public Response<Set<EnrollmentDto>> getEnrollments(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Enrollment> enrollmentPage = enrollmentRepository.findAll(pageable);

        return Response.<Set<EnrollmentDto>>builder()
                .message("دروس دانشجویان:")
                .statusCode(HttpStatus.OK.value())
                .data(enrollmentPage.get().map(EntityMapper::toEnrollmentDto).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Response<String> deleteEnrollment(long studentId, long courseId) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new NotFoundException("دانشجو چنین درسی برنداشته است!"));
        enrollmentRepository.delete(enrollment);

        return Response.<String>builder()
                .data("درس انتخابی دانشجو حذف شد!")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
