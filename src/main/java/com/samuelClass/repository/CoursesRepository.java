package com.samuelClass.repository;

import com.samuelClass.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Long> {
}