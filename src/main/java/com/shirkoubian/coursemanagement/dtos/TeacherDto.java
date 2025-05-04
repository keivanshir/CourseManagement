package com.shirkoubian.coursemanagement.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Set;

public class TeacherDto {

    private Long id;
    private String personnelNumber;

    @Size(max = 48)
    @Pattern(regexp = "^[A-Za-z]+$")
    private String firstName;

    @Size(max = 48)
    @Pattern(regexp = "^[A-Za-z]+$")
    private String lastName;
    private String nationalCode;
    private Set<CourseDto> courseDtos;
    private DepartmentDto departmentDto;
    private DepartmentDto departmentForManagerDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Set<CourseDto> getCourseDtos() {
        return courseDtos;
    }

    public void setCourseDtos(Set<CourseDto> courseDtos) {
        this.courseDtos = courseDtos;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }

    public DepartmentDto getDepartmentForManagerDto() {
        return departmentForManagerDto;
    }

    public void setDepartmentForManagerDto(DepartmentDto departmentForManagerDto) {
        this.departmentForManagerDto = departmentForManagerDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDto that = (TeacherDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(personnelNumber, that.personnelNumber)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(nationalCode, that.nationalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personnelNumber, firstName, lastName, nationalCode);
    }
}
