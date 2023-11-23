package com.samuelClass.controllers;

import com.samuelClass.dto.request.StudentRegDto;
import com.samuelClass.dto.request.TeacherRegDto;
import com.samuelClass.model.Student;
import com.samuelClass.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(
            summary = "Create a new Student"
    )
    @ApiResponse(responseCode = "201", description = "created Student")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String StudentRegistration(@RequestBody StudentRegDto studentRegDto){
        log.info("TeacherController registers teachers: {}", studentRegDto.email());
        return studentService.StudentRegistration(studentRegDto);
    }

    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public Student getStudentbyId(@PathVariable Long id){
        return studentService.getStudentById(id);
    }


    @GetMapping("/{level}")
    public List<Student> getStudentbyLevel(@PathVariable String level){
        return studentService.getStudentByLevel(level);
    }


}
