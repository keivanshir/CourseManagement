package com.shirkoubian.coursemanagement.repositories;

import com.shirkoubian.coursemanagement.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT sum(E.grade.gradeValue * C.unit) / sum(C.unit)  FROM Course AS C " +
            " INNER JOIN Enrollment AS E" +
            " WHERE C.teacher.id =: teacherId")
    Optional<Double> getAverageGradeOfStudentsOfTeacherByEachCourse(@Param("teacherId") long teacherId);
}
