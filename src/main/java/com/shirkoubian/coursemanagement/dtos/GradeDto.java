package com.shirkoubian.coursemanagement.dtos;

import java.util.Objects;

public class GradeDto {

    private Long id;
    private EnrollmentDto enrollmentDto;
    private Double grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnrollmentDto getEnrollmentDto() {
        return enrollmentDto;
    }

    public void setEnrollmentDto(EnrollmentDto enrollmentDto) {
        this.enrollmentDto = enrollmentDto;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeDto gradeDto = (GradeDto) o;
        return Objects.equals(id, gradeDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
