package com.samuelClass.services;

import com.samuelClass.model.Student;
import com.samuelClass.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
}
