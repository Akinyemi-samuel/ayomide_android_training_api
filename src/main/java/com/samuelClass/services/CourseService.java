package com.samuelClass.services;

import com.samuelClass.dto.request.CourseDto;
import com.samuelClass.model.Courses;
import com.samuelClass.repository.CoursesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CoursesRepository coursesRepository;

    public List<Courses> getAllCourses(){
        return coursesRepository.findAll();
    }

    public String AddCourse(CourseDto courseDto){
        Courses course = Courses.builder()
                .courseName(courseDto.courseName())
                .build();

        coursesRepository.save(course);
        return courseDto.courseName()+ " has been successfully added to the list of courses";
    }

    @Transactional
    public String deleteCourse(Long courseId){
        coursesRepository.deleteById(courseId);
        return "course has been successfully deleted from the list of courses";
    }
}
