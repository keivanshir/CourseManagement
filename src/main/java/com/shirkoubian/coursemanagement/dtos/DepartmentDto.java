package com.shirkoubian.coursemanagement.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DepartmentDto {

    private Long id;
    @Size(min = 5, max = 48, message = "نام دانشکده باید بین 5 تا 48 کاراکتر باشد!")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "نام دانشکده تنها از حروف و اعداد باید تشکیل شود!")
    private String name;
    private TeacherDto groupManager;
    private Set<TeacherDto> teacherDtos = new HashSet<>();
    private Set<StudentDto> studentDtos = new HashSet<>();
    private Set<CourseDto> courseDtos = new HashSet<>();

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

    public TeacherDto getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(TeacherDto groupManager) {
        this.groupManager = groupManager;
    }

    public Set<TeacherDto> getTeacherDtos() {
        return teacherDtos;
    }

    public void setTeacherDtos(Set<TeacherDto> teacherDtos) {
        this.teacherDtos = teacherDtos;
    }

    public Set<StudentDto> getStudentDtos() {
        return studentDtos;
    }

    public void setStudentDtos(Set<StudentDto> studentDtos) {
        this.studentDtos = studentDtos;
    }

    public Set<CourseDto> getCourseDtos() {
        return courseDtos;
    }

    public void setCourseDtos(Set<CourseDto> courseDtos) {
        this.courseDtos = courseDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDto that = (DepartmentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
