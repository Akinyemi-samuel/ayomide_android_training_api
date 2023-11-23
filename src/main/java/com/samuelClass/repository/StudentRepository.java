package com.samuelClass.repository;

import com.samuelClass.model.Level;
import com.samuelClass.model.Student;
import com.samuelClass.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<List<Student>> findByLevel(Level level);
}
