package com.shirkoubian.coursemanagement.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class CourseDto {

    private Long id;

    @Size(max = 64, message = "نام درس باید کمتر از 64 کاراکتر باشد!")
    @Pattern(regexp = "^[A-Za-z0-9]+$",  message = "نام درس تنها از حروف و اعداد باید تشکیل شود!")
    private String name;

    private Byte unit;

    private Set<EnrollmentDto> enrollmentDTOs = new HashSet<>();
    private TeacherDto teacherDto;
    private DepartmentDto departmentDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getUnit() {
        return unit;
    }

    public void setUnit(Byte unit) {
        this.unit = unit;
    }

    public Set<EnrollmentDto> getEnrollmentDTOs() {
        return enrollmentDTOs;
    }

    public void setEnrollmentDTOs(Set<EnrollmentDto> enrollmentDTOs) {
        this.enrollmentDTOs = enrollmentDTOs;
    }

    public TeacherDto getTeacherDto() {
        return teacherDto;
    }

    public void setTeacherDto(TeacherDto teacherDto) {
        this.teacherDto = teacherDto;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDto courseDto = (CourseDto) o;
        return Objects.equals(id, courseDto.id) && Objects.equals(name, courseDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
