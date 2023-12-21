package com.samuelClass.controllers;

import com.samuelClass.dto.request.CourseDto;
import com.samuelClass.model.Courses;
import com.samuelClass.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("course")
@RequiredArgsConstructor
@Tag(name = "Course")
public class CourseController {

    private final CourseService courseService;

    @Operation(
            summary = "Gets all courses"
    )
    @ApiResponse(responseCode = "200", description = "returns a list of all courses")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Courses> getAllCourses() {
        log.info("CourseController lists of courses: {}");
        return courseService.getAllCourses();
    }

    @Operation(
            summary = "Creates a new courses"
    )
    @ApiResponse(responseCode = "201", description ="courseDto.courseName()+ \" has been successfully added to the list of courses\"")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String AddCourse(@RequestBody CourseDto courseDto) {
        log.info("CourseController add new course: {}", courseDto.courseName());
       return courseService.AddCourse(courseDto);

    }
}
