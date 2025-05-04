package com.shirkoubian.coursemanagement.repositories;

import com.shirkoubian.coursemanagement.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


}
