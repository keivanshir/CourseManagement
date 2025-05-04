package com.shirkoubian.coursemanagement.repositories;

import com.shirkoubian.coursemanagement.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
