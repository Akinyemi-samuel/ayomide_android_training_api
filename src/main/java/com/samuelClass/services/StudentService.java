package com.samuelClass.services;

import com.samuelClass.dto.request.StudentRegDto;
import com.samuelClass.dto.request.TeacherRegDto;
import com.samuelClass.exception.ApiException;
import com.samuelClass.model.Level;
import com.samuelClass.model.Role;
import com.samuelClass.model.Student;
import com.samuelClass.model.Teacher;
import com.samuelClass.repository.StudentRepository;
import com.samuelClass.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final IsEmailValid isEmailValid;
    private final TeacherRepository teacherRepository;
    private final IsPasswordValid isPasswordValid;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    public String StudentRegistration(StudentRegDto studentRegDto) {

        if (!isEmailValid.test(studentRegDto.email()))
            throw new ApiException("Invalid Email Found", HttpStatus.NOT_ACCEPTABLE);

        Optional<Student> studentOptional = studentRepository.findByEmail(studentRegDto.email());
        if (studentOptional.isPresent()) {
            throw new ApiException("User Already Exists", HttpStatus.NOT_FOUND);
        }

        Student student = Student.builder()
                .firstName(studentRegDto.firstName())
                .lastName(studentRegDto.lastName())
                .level(Level.valueOf(studentRegDto.level()))
                .email(studentRegDto.email())
                .teacher(studentRegDto.teacher())
                .build();

        studentRepository.save(student);
        return "Registration successful!";

    }

    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
}
