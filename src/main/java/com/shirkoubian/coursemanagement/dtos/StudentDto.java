package com.shirkoubian.coursemanagement.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Set;

public class StudentDto {

    private Long id;
    private String studentNumber;

    @Size(max = 48)
    @Pattern(regexp = "^[A-Za-z]+$")
    private String firstName;

    @Size(max = 48)
    @Pattern(regexp = "^[A-Za-z]+$")
    private String lastName;
    private String nationalCode;

    @Size(max = 128)
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String address;
    private Set<EnrollmentDto> enrollmentDTOs;
    private DepartmentDto departmentDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<EnrollmentDto> getEnrollmentDTOs() {
        return enrollmentDTOs;
    }

    public void setEnrollmentDTOs(Set<EnrollmentDto> enrollmentDTOs) {
        this.enrollmentDTOs = enrollmentDTOs;
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
        StudentDto that = (StudentDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(studentNumber, that.studentNumber)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(nationalCode, that.nationalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentNumber, firstName, lastName, nationalCode);
    }
}
