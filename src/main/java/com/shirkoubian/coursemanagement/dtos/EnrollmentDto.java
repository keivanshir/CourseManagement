package com.shirkoubian.coursemanagement.dtos;

import java.util.Objects;

public class EnrollmentDto {

    private StudentDto studentDto;
    private CourseDto courseDto;
    private GradeDto gradeDto;

    public StudentDto getStudentDto() {
        return studentDto;
    }

    public void setStudentDto(StudentDto studentDto) {
        this.studentDto = studentDto;
    }

    public CourseDto getCourseDto() {
        return courseDto;
    }

    public void setCourseDto(CourseDto courseDto) {
        this.courseDto = courseDto;
    }

    public GradeDto getGradeDto() {
        return gradeDto;
    }

    public void setGradeDto(GradeDto gradeDto) {
        this.gradeDto = gradeDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentDto that = (EnrollmentDto) o;
        return Objects.equals(studentDto, that.studentDto) && Objects.equals(courseDto, that.courseDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentDto, courseDto);
    }
}
