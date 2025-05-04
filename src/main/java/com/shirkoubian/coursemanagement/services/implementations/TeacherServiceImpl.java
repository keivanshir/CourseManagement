package com.shirkoubian.coursemanagement.services.implementations;

import com.shirkoubian.coursemanagement.dtos.EntityMapper;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.dtos.TeacherDto;
import com.shirkoubian.coursemanagement.entities.Course;
import com.shirkoubian.coursemanagement.entities.Teacher;
import com.shirkoubian.coursemanagement.exception.CalculationException;
import com.shirkoubian.coursemanagement.exception.DepartmentNotEqualException;
import com.shirkoubian.coursemanagement.exception.NotFoundException;
import com.shirkoubian.coursemanagement.repositories.CourseRepository;
import com.shirkoubian.coursemanagement.repositories.TeacherRepository;
import com.shirkoubian.coursemanagement.services.interfaces.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public Response<TeacherDto> createTeacher(TeacherDto teacherDto) {
        Teacher teacher = EntityMapper.toTeacherEntity(teacherDto);
        Teacher savedTeacher = teacherRepository.save(teacher);

        return Response.<TeacherDto>builder()
                .message("استاد ثبت شد!")
                .statusCode(HttpStatus.OK.value())
                .data(EntityMapper.toTeacherDTO(savedTeacher))
                .build();
    }

    @Override
    public Response<TeacherDto> updateTeacher(TeacherDto teacherDto, long teacherId) {
        Teacher teacherToUpdate;
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isPresent()){
            teacherToUpdate = EntityMapper.toTeacherEntity(teacherDto);
            teacherToUpdate.setId(teacher.get().getId());
            Teacher updatedTeacher = teacherRepository.save(teacherToUpdate);
            return Response.<TeacherDto>builder()
                    .message("استاد به روزرسانی شد!")
                    .statusCode(HttpStatus.OK.value())
                    .data(EntityMapper.toTeacherDTO(updatedTeacher))
                    .build();
        } else
            throw new NotFoundException("استاد با شناسه " + teacherId + " پیدا نشد!");
    }

    @Override
    public Response<Set<TeacherDto>> getTeachers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Teacher> teacherPage = teacherRepository.findAll(pageable);

        return Response.<Set<TeacherDto>>builder()
                .message("اساتید:")
                .statusCode(HttpStatus.OK.value())
                .data(teacherPage.get().map(EntityMapper::toTeacherDTO).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Response<String> deleteTeacher(long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new NotFoundException("استاد با شناسه " + teacherId + " پیدا نشد!"));
        teacherRepository.delete(teacher);
        return Response.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("استاد حذف شد!")
                .build();
    }

    @Override
    public Response<TeacherDto> addTeacherCourse(long teacherId, long courseId) {

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new NotFoundException("استاد با شناسه " + teacherId + " پیدا نشد!"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("درس با شناسه " + courseId + " پیدا نشد!"));

        Teacher updatedTeacher;
        if (course.getDepartment().equals(teacher.getDepartment())){
            teacher.getCourses().add(course);
            updatedTeacher = teacherRepository.save(teacher);
        } else {
            throw new DepartmentNotEqualException("دانشکده استاد با درس باید یکسان باشد!");
        }
        return Response.<TeacherDto>builder()
                .message("درس " + course.getName() + " برای استاد" +
                        teacher.getFirstName() + " " + teacher.getLastName() + " اضافه شد!")
                .statusCode(HttpStatus.OK.value())
                .data(EntityMapper.toTeacherDTO(updatedTeacher))
                .build();
    }

    @Override
    public Response<Double> getAverageGradeOfTeacherStudentsByEachCourse(long teacherId) {
        Double average = courseRepository.getAverageGradeOfStudentsOfTeacherByEachCourse(teacherId)
                .orElseThrow(() -> new CalculationException("خطا در محاسبه میانگین نمرات استاد با شناسه " + teacherId));
        return Response.<Double>builder()
                .message(" میانگین معدل دانشجویان استاد با شناسه " + teacherId + " به تفکیک درس")
                .statusCode(HttpStatus.OK.value())
                .data(average)
                .build();
    }
}
