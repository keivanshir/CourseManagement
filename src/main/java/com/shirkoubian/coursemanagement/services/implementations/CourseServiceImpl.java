package com.shirkoubian.coursemanagement.services.implementations;

import com.shirkoubian.coursemanagement.dtos.CourseDto;
import com.shirkoubian.coursemanagement.dtos.EntityMapper;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.entities.Course;
import com.shirkoubian.coursemanagement.exception.NotFoundException;
import com.shirkoubian.coursemanagement.repositories.CourseRepository;
import com.shirkoubian.coursemanagement.services.interfaces.CourseService;
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
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public Response<CourseDto> createCourse(CourseDto courseDto) {
        Course course = EntityMapper.toCourseEntity(courseDto);
        Course savedCourse = courseRepository.save(course);

        return Response.<CourseDto>builder()
                .message("درس ثبت شد!")
                .statusCode(HttpStatus.OK.value())
                .data(EntityMapper.toCourseDTO(savedCourse))
                .build();
    }

    @Override
    public Response<Set<CourseDto>> getCourses(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Course> coursePage = courseRepository.findAll(pageable);

        return Response.<Set<CourseDto>>builder()
                .message("دروس:")
                .statusCode(HttpStatus.OK.value())
                .data(coursePage.get().map(EntityMapper::toCourseDTO).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Response<CourseDto> updateCourse(CourseDto courseDto, long courseId) {
        Course courseToUpdate;
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            courseToUpdate = EntityMapper.toCourseEntity(courseDto);
            courseToUpdate.setId(course.get().getId());
            Course updatedCourse = courseRepository.save(courseToUpdate);
            return Response.<CourseDto>builder()
                    .message("درس به روزرسانی شد!")
                    .statusCode(HttpStatus.OK.value())
                    .data(EntityMapper.toCourseDTO(updatedCourse))
                    .build();
        } else
            throw new NotFoundException("درس با شناسه " + courseId + " پیدا نشد!");
    }

    @Override
    public Response<String> deleteCourse(long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("درس با شناسه " + courseId + " پیدا نشد!"));
        courseRepository.delete(course);
        return Response.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("درس حذف شد!")
                .build();
    }
}
