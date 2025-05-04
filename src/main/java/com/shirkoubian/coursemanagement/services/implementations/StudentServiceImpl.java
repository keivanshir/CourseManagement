package com.shirkoubian.coursemanagement.services.implementations;

import com.shirkoubian.coursemanagement.dtos.*;
import com.shirkoubian.coursemanagement.entities.Course;
import com.shirkoubian.coursemanagement.entities.Student;
import com.shirkoubian.coursemanagement.exception.CalculationException;
import com.shirkoubian.coursemanagement.exception.NotFoundException;
import com.shirkoubian.coursemanagement.repositories.CourseRepository;
import com.shirkoubian.coursemanagement.repositories.EnrollmentRepository;
import com.shirkoubian.coursemanagement.repositories.StudentRepository;
import com.shirkoubian.coursemanagement.services.interfaces.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              CourseRepository courseRepository,
                              EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Response<StudentDto> createStudent(StudentDto studentDto) {
        Student student = EntityMapper.toStudentEntity(studentDto);
        Student savedStudent = studentRepository.save(student);

        return Response.<StudentDto>builder()
                .message("دانشجو ثبت شد!")
                .statusCode(HttpStatus.OK.value())
                .data(EntityMapper.toStudentDTO(savedStudent))
                .build();
    }

    @Override
    public Response<StudentDto> updateStudent(StudentDto studentDto, long studentId) {
        Student studentToUpdate;
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            studentToUpdate = EntityMapper.toStudentEntity(studentDto);
            studentToUpdate.setId(student.get().getId());
            Student updatedStudent = studentRepository.save(studentToUpdate);
            return Response.<StudentDto>builder()
                    .message("دانشجو به روزرسانی شد!")
                    .statusCode(HttpStatus.OK.value())
                    .data(EntityMapper.toStudentDTO(updatedStudent))
                    .build();
        } else
            throw new NotFoundException("دانشجو با شناسه " + studentId + " پیدا نشد!");
    }

    @Override
    public Response<Set<StudentDto>> getStudents(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Student> studentPage = studentRepository.findAll(pageable);

        return Response.<Set<StudentDto>>builder()
                .message("دانشجویان:")
                .statusCode(HttpStatus.OK.value())
                .data(studentPage.get().map(EntityMapper::toStudentDTO).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Response<String> deleteStudent(long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("دانشجو با شناسه " + studentId + " پیدا نشد!"));
        studentRepository.delete(student);
        return Response.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("دانشجو حذف شد!")
                .build();
    }

    @Override
    public Response<CourseDto> addStudentCourse(long studentId, long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("دانشجو با شناسه " + studentId + " پیدا نشد!"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("درس با شناسه " + courseId + " پیدا نشد!"));

        EnrollmentDto dto = new EnrollmentDto();
        dto.setStudentDto(EntityMapper.toStudentDTO(student));
        dto.setCourseDto(EntityMapper.toCourseDTO(course));

        course.getEnrollments().add(EntityMapper.toEnrollmentEntity(dto));

        Course savedCourse = courseRepository.save(course);

        return Response.<CourseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .message("درس برای دانشجو " + student.getStudentNumber() + " اضافه شد!")
                .data(EntityMapper.toCourseDTO(savedCourse))
                .build();
    }

    @Override
    public Response<Object> getAverageGradesOfStudent(long studentId) {
        Double studentAverage = enrollmentRepository.getAverageGradeOfStudent(studentId)
                .orElseThrow(() -> new CalculationException("خطا در محاسبه میانگین نمرات دانشجو با شناسه " + studentId));

        return Response.builder()
                .message("میانگین نمرات دانشجو")
                .statusCode(2)
                .data(studentAverage)
                .build();
    }
}
