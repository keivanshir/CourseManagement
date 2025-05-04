package com.shirkoubian.coursemanagement.repositories;

import com.shirkoubian.coursemanagement.entities.Enrollment;
import com.shirkoubian.coursemanagement.entities.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    @Query("SELECT sum(E.grade.gradeValue * C.unit) / sum(C.unit) FROM " +
            " Enrollment as E" +
            " INNER JOIN Course as C " +
            " WHERE E.student.department.id = :departmentId")
    Optional<Double> getAverageGradeOfStudentsInDepartment(@Param("departmentId") long departmentId);

    @Query("SELECT sum(E.grade.gradeValue * C.unit) / sum(C.unit) FROM " +
            " Enrollment as E" +
            " INNER JOIN Course as C " +
            " WHERE E.student.id = :studentId")
    Optional<Double> getAverageGradeOfStudent(@Param("studentId") long studentId);
}
