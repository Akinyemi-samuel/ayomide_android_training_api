package com.samuelClass.services;

import com.samuelClass.config.JwtService;
import com.samuelClass.dto.request.AuthenticationDto;
import com.samuelClass.dto.request.RegistrationDto;
import com.samuelClass.dto.request.TeacherRegDto;
import com.samuelClass.dto.response.AuthenticationResponse;
import com.samuelClass.exception.ApiException;
import com.samuelClass.model.Level;
import com.samuelClass.model.Role;
import com.samuelClass.model.Teacher;
import com.samuelClass.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherService {

    private final IsEmailValid isEmailValid;
    private final TeacherRepository teacherRepository;
    private final IsPasswordValid isPasswordValid;
    private final PasswordEncoder passwordEncoder;

    public String UserRegistration(TeacherRegDto teacherRegDto) {

        if (!isEmailValid.test(teacherRegDto.email()))
            throw new ApiException("Invalid Email Found", HttpStatus.NOT_ACCEPTABLE);

        if (!isPasswordValid.test(teacherRegDto.password()))
            throw new ApiException("Password is Invalid", HttpStatus.NOT_ACCEPTABLE);

        Optional<Teacher> userOptional = teacherRepository.findByEmail(teacherRegDto.email());
        if (userOptional.isPresent()) {
            throw new ApiException("User Already Exists", HttpStatus.NOT_FOUND);
        }

        Teacher teacher = Teacher.builder()
                .firstName(teacherRegDto.firstName())
                .lastName(teacherRegDto.lastName())
                .role(Role.valueOf(teacherRegDto.role()))
                .level(Level.valueOf(teacherRegDto.level()))
                .email(teacherRegDto.email())
                .password(passwordEncoder.encode(teacherRegDto.password()) )
                .build();

        teacherRepository.save(teacher);
        return "Registration successful!";

    }

    public List<Teacher> getAllTeachersWithStudent() {
        return teacherRepository.findAll();
    }


    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll().stream().map(u ->{
            return Teacher.builder()
                    .id(u.getId())
                    .role(u.getRole())
                    .firstName(u.getFirstName())
                    .level(u.getLevel())
                    .lastName(u.getLastName())
                    .email(u.getEmail())
                    .password(u.getPassword())
                    .build();
        }).collect(Collectors.toList());
    }



}
