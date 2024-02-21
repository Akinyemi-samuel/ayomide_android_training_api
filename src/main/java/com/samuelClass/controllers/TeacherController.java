package com.samuelClass.controllers;

import com.samuelClass.dto.request.TeacherRegDto;
import com.samuelClass.model.Teacher;
import com.samuelClass.services.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
@Tag(name = "Teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(
            summary = "Create a new teacher"
    )
    @ApiResponse(responseCode = "201", description = "created Teacher")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String userRegistration(@RequestBody TeacherRegDto teacherRegDto){
        log.info("TeacherController registers teachers: {}", teacherRegDto.email());
        return teacherService.UserRegistration(teacherRegDto);
    }

    @Operation(
            summary = "get a list of all teachers with their corresponding students"
    )
    @ApiResponse(responseCode = "200", description = "get all teachers with their corresponding students")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/student")
    public List<Teacher> getAllTeachersWithStudent() {
        return teacherService.getAllTeachersWithStudent();
    }


    @Operation(
            summary = "get a list of all teachers"
    )
    @ApiResponse(responseCode = "200", description = "get all teachers")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }


    @Operation(
            summary = "DELETE TEACHER BY ID"
    )
    @ApiResponse(responseCode = "201", description ="Teacher has been successfully deleted from the list of teachers\"")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public String deleteTeacher(@PathVariable(name = "id") Long id) {
        log.info("TeacherController deleted a teacher: {}", id);
        return teacherService.deleteTeacher(id);

    }
}
