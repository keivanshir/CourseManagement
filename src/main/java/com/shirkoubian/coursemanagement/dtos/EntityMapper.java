package com.shirkoubian.coursemanagement.dtos;

import com.shirkoubian.coursemanagement.entities.*;

import java.util.stream.Collectors;

public class EntityMapper {

    public static Course toCourseEntity(CourseDto courseDto){
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setUnit(courseDto.getUnit());
        course.setName(courseDto.getName());
        if (courseDto.getTeacherDto() != null)
            course.setTeacher(toTeacherEntity(courseDto.getTeacherDto()));
        if (courseDto.getEnrollmentDTOs() != null && !courseDto.getEnrollmentDTOs().isEmpty()){
            course.setEnrollments(courseDto.getEnrollmentDTOs().stream()
                    .map(EntityMapper::toEnrollmentEntity)
                    .collect(Collectors.toSet()));
        }
        if (courseDto.getDepartmentDto() != null)
            course.setDepartment(toDepartmentEntity(courseDto.getDepartmentDto()));
        return course;
    }

    public static CourseDto toCourseDTO(Course course){
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setUnit(course.getUnit());
        courseDto.setName(course.getName());
        if (course.getTeacher() != null)
            courseDto.setTeacherDto(toTeacherDTO(course.getTeacher()));
        if (course.getEnrollments() != null && !course.getEnrollments().isEmpty()){
            courseDto.setEnrollmentDTOs(course.getEnrollments().stream()
                    .map(EntityMapper::toEnrollmentDto)
                    .collect(Collectors.toSet()));
        }
        if (course.getDepartment() != null)
            courseDto.setDepartmentDto(toDepartmentDTO(course.getDepartment()));

        return courseDto;
    }

    public static TeacherDto toTeacherDTO(Teacher teacher){
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setNationalCode(teacher.getNationalCode());
        teacherDto.setPersonnelNumber(teacher.getPersonnelNumber());
        if (teacher.getDepartment() != null)
            teacherDto.setDepartmentDto(toDepartmentDTO(teacher.getDepartment()));
        if (teacher.getCourses() != null && !teacher.getCourses().isEmpty()){
            teacherDto.setCourseDtos(teacher.getCourses().stream()
                    .map(EntityMapper::toCourseDTO)
                    .collect(Collectors.toSet()));
        }
        if (teacher.getDepartmentForManager() != null){
            teacherDto.setDepartmentForManagerDto(toDepartmentDTO(teacher.getDepartmentForManager()));
        }

        return teacherDto;

    }

    public static Teacher toTeacherEntity(TeacherDto teacherDto){
        Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setNationalCode(teacherDto.getNationalCode());
        teacher.setPersonnelNumber(teacherDto.getPersonnelNumber());
        if (teacherDto.getDepartmentDto() != null)
            teacher.setDepartment(toDepartmentEntity(teacherDto.getDepartmentDto()));
        if (teacherDto.getCourseDtos() != null && !teacherDto.getCourseDtos().isEmpty()){
            teacher.setCourses(teacherDto.getCourseDtos().stream()
                    .map(EntityMapper::toCourseEntity)
                    .collect(Collectors.toSet()));
        }
        if (teacherDto.getDepartmentForManagerDto() != null){
            teacher.setDepartmentForManager(toDepartmentEntity(teacherDto.getDepartmentForManagerDto()));
        }

        return teacher;
    }



    public static StudentDto toStudentDTO(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setStudentNumber(student.getStudentNumber());
        studentDto.setNationalCode(student.getNationalCode());
        studentDto.setAddress(student.getAddress());
        if (student.getEnrollments() != null && !student.getEnrollments().isEmpty()){
            studentDto.setEnrollmentDTOs(student.getEnrollments().stream()
                    .map(EntityMapper::toEnrollmentDto)
                    .collect(Collectors.toSet()));
        }
        if (student.getDepartment() != null){
            studentDto.setDepartmentDto(toDepartmentDTO(student.getDepartment()));
        }
        return studentDto;
    }

    public static Student toStudentEntity(StudentDto studentDto){
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setStudentNumber(studentDto.getStudentNumber());
        student.setNationalCode(studentDto.getNationalCode());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAddress(studentDto.getAddress());
        if (studentDto.getEnrollmentDTOs() != null && !studentDto.getEnrollmentDTOs().isEmpty()){
            student.setEnrollments(studentDto.getEnrollmentDTOs().stream()
                    .map(EntityMapper::toEnrollmentEntity)
                    .collect(Collectors.toSet()));
        }
        if (studentDto.getDepartmentDto() != null)
            student.setDepartment(toDepartmentEntity(studentDto.getDepartmentDto()));
        return student;
    }

    public static DepartmentDto toDepartmentDTO(Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        if (department.getGroupManager() != null)
            departmentDto.setGroupManager(toTeacherDTO(department.getGroupManager()));
        if (department.getCourses() != null && !department.getCourses().isEmpty()){
            departmentDto.setCourseDtos(department.getCourses().stream()
                    .map(EntityMapper::toCourseDTO)
                    .collect(Collectors.toSet()));
        }
        if (department.getStudents() != null && !department.getStudents().isEmpty()){
            departmentDto.setStudentDtos(department.getStudents().stream()
                    .map(EntityMapper::toStudentDTO)
                    .collect(Collectors.toSet()));
        }
        if (department.getTeachers() != null && !department.getTeachers().isEmpty()){
            departmentDto.setTeacherDtos(department.getTeachers().stream()
                    .map(EntityMapper::toTeacherDTO)
                    .collect(Collectors.toSet()));
        }
        return departmentDto;
    }

    public static Department toDepartmentEntity(DepartmentDto departmentDto){
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        if (departmentDto.getGroupManager() != null)
            department.setGroupManager(toTeacherEntity(departmentDto.getGroupManager()));
        if (departmentDto.getCourseDtos() != null && !departmentDto.getCourseDtos().isEmpty()){
            department.setCourses(departmentDto.getCourseDtos().stream()
                    .map(EntityMapper::toCourseEntity)
                    .collect(Collectors.toSet()));
        }
        if (departmentDto.getStudentDtos() != null && !departmentDto.getStudentDtos().isEmpty()){
            department.setStudents(departmentDto.getStudentDtos().stream()
                    .map(EntityMapper::toStudentEntity)
                    .collect(Collectors.toSet()));
        }
        if (departmentDto.getTeacherDtos() != null && !departmentDto.getTeacherDtos().isEmpty()){
            department.setTeachers(departmentDto.getTeacherDtos().stream()
                    .map(EntityMapper::toTeacherEntity)
                    .collect(Collectors.toSet()));
        }
        return department;
    }

    public static Enrollment toEnrollmentEntity(EnrollmentDto enrollmentDto){
        Enrollment enrollment = new Enrollment();
        if (enrollmentDto.getCourseDto() != null && enrollmentDto.getStudentDto() != null){
            enrollment.setId(new EnrollmentId(enrollmentDto.getStudentDto().getId(), enrollmentDto.getCourseDto().getId()));
            enrollment.setCourse(toCourseEntity(enrollmentDto.getCourseDto()));
            enrollment.setStudent(toStudentEntity(enrollmentDto.getStudentDto()));
        }
        if (enrollmentDto.getGradeDto() != null)
            enrollment.setGrade(toGradeEntity(enrollmentDto.getGradeDto()));
        return enrollment;
    }

    public static EnrollmentDto toEnrollmentDto(Enrollment enrollment){
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        if (enrollment.getCourse() != null)
            enrollmentDto.setCourseDto(toCourseDTO(enrollment.getCourse()));
        if (enrollment.getStudent() != null)
            enrollmentDto.setStudentDto(toStudentDTO(enrollment.getStudent()));
        if (enrollment.getGrade() != null)
            enrollmentDto.setGradeDto(toGradeDTO(enrollment.getGrade()));

        return enrollmentDto;
    }

    public static GradeDto toGradeDTO(Grade grade) {
        GradeDto gradeDto = new GradeDto();
        gradeDto.setId(grade.getId());
        if (grade.getEnrollment() != null){
            gradeDto.setEnrollmentDto(toEnrollmentDto(grade.getEnrollment()));
        }
        gradeDto.setGrade(grade.getGradeValue());
        return gradeDto;

    }

    public static Grade toGradeEntity(GradeDto gradeDto) {
        Grade grade = new Grade();
        grade.setId(gradeDto.getId());
        if (gradeDto.getEnrollmentDto() != null){
            grade.setEnrollment(toEnrollmentEntity(gradeDto.getEnrollmentDto()));
        }
        grade.setGradeValue(gradeDto.getGrade());
        return grade;

    }
}
