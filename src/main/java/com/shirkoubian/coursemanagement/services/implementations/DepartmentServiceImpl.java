package com.shirkoubian.coursemanagement.services.implementations;

import com.shirkoubian.coursemanagement.dtos.DepartmentDto;
import com.shirkoubian.coursemanagement.dtos.EntityMapper;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.dtos.TeacherDto;
import com.shirkoubian.coursemanagement.entities.Department;
import com.shirkoubian.coursemanagement.exception.CalculationException;
import com.shirkoubian.coursemanagement.exception.NotFoundException;
import com.shirkoubian.coursemanagement.exception.TeacherNotInDepartmentException;
import com.shirkoubian.coursemanagement.repositories.DepartmentRepository;
import com.shirkoubian.coursemanagement.repositories.EnrollmentRepository;
import com.shirkoubian.coursemanagement.services.interfaces.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EnrollmentRepository enrollmentRepository) {
        this.departmentRepository = departmentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Response<DepartmentDto> createDepartment(DepartmentDto departmentDto) {

        Department department = EntityMapper.toDepartmentEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);

        return Response.<DepartmentDto>builder()
                .message("دانشکده ذخیره شد!")
                .statusCode(HttpStatus.CREATED.value())
                .data(EntityMapper.toDepartmentDTO(savedDepartment))
                .build();
    }

    @Override
    public Response<Set<DepartmentDto>> getDepartments(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Department> departmentPage = departmentRepository.findAll(pageable);

        return Response.<Set<DepartmentDto>>builder()
                .message("دانشکده ها:")
                .statusCode(HttpStatus.OK.value())
                .data(departmentPage.get().map(EntityMapper::toDepartmentDTO).collect(Collectors.toSet()))
                .build();
    }


    @Override
    public Response<DepartmentDto> updateDepartment(DepartmentDto departmentDto, long departmentId) {
        Department departmentToUpdate;
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()){
            departmentToUpdate = EntityMapper.toDepartmentEntity(departmentDto);
            departmentToUpdate.setId(department.get().getId());
            Department updatedDepartment = departmentRepository.save(departmentToUpdate);
            return Response.<DepartmentDto>builder()
                    .message("دانشکده به روزرسانی شد!")
                    .statusCode(HttpStatus.OK.value())
                    .data(EntityMapper.toDepartmentDTO(updatedDepartment))
                    .build();
        } else
            throw new NotFoundException("دانشکده با شناسه " + departmentId + " پیدا نشد!");


    }

    @Override
    public Response<String> deleteDepartment(long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("دانشکده با شناسه " + departmentId + " پیدا نشد!"));
        departmentRepository.delete(department);
        return Response.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("دانشکده حذف شد!")
                .build();
    }

    @Override
    public Response<String> addGroupManagerToDepartment(TeacherDto teacherDto, long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("دانشکده با شناسه " + departmentId + " پیدا نشد!"));
        if (department.getTeachers().contains(EntityMapper.toTeacherEntity(teacherDto))){
            department.setGroupManager(EntityMapper.toTeacherEntity(teacherDto));
            departmentRepository.save(department);
        } else throw new TeacherNotInDepartmentException("مدیر گروه باید از اساتید همین دانشکده باشد!");

        return Response.<String>builder()
                .message("استاد " + teacherDto.getFirstName() + " " + teacherDto.getLastName() + " به عنوان مدیر گروه ثبت شد!")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public Response<String> addTeacherToDepartment(TeacherDto teacherDto, long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("دانشکده با شناسه " + departmentId + " پیدا نشد!"));
        department.getTeachers().add(EntityMapper.toTeacherEntity(teacherDto));
        departmentRepository.save(department);
        return Response.<String>builder()
                .message("استاد " + teacherDto.getFirstName() + " " + teacherDto.getLastName() + " ثبت شد!")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public Response<Object> getAverageGradeOfDepartmentStudents(long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("دانشکده با شناسه " + departmentId + " پیدا نشد!"));

        Double studentAverage = enrollmentRepository.getAverageGradeOfStudentsInDepartment(department.getId())
                .orElseThrow(() -> new CalculationException("خطا در محاسبه میانگین نمرات دانشجویان دانشکده با شناسه " + departmentId));

        return Response.builder()
                .message("میانگین نمرات دانشجویان دانشکده " + department.getName())
                .statusCode(HttpStatus.OK.value())
                .data(studentAverage)
                .build();
    }
}
