package com.shirkoubian.coursemanagement.repositories;

import com.shirkoubian.coursemanagement.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
