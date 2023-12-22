package com.samuelClass;

import com.samuelClass.dto.request.CourseDto;
import com.samuelClass.dto.request.StudentRegDto;
import com.samuelClass.dto.request.TeacherRegDto;
import com.samuelClass.model.Courses;
import com.samuelClass.model.Level;
import com.samuelClass.model.Role;
import com.samuelClass.model.Teacher;
import com.samuelClass.repository.TeacherRepository;
import com.samuelClass.services.CourseService;
import com.samuelClass.services.StudentService;
import com.samuelClass.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class AyomideAndroidApiApplication implements CommandLineRunner {

    private final TeacherRepository teacherRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public static void main(String[] args) {
        SpringApplication.run(AyomideAndroidApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        List<Teacher> teacherRegDtoList = List.of(
                new Teacher("Samuel", "Akinyemi", "samuel@gmail.com", Role.TEACHER, Level.JSS1, "samuel"),
                new Teacher("Joseph", "Akinyemi", "joseph@gmail.com", Role.TEACHER, Level.JSS1, "samuel"),
                new Teacher("Grace", "Ayomide", "grace@gmail.com", Role.TEACHER, Level.JSS1, "samuel")
        );

        for (Teacher t : teacherRegDtoList) {
            teacherRepository.save(t);
        }

        List<StudentRegDto> studentRegDtoList = List.of(
                new StudentRegDto("Samuel", "Akinyemi", "samuel@gmail.com", "JSS1",  teacherRegDtoList.get(0)),
                new StudentRegDto("Joseph", "Akinyemi", "joseph@gmail.com", "JSS1", teacherRegDtoList.get(0)),
                new StudentRegDto("Mary", "magdalin", "mary@gmail.com", "JSS2", teacherRegDtoList.get(1)),
                new StudentRegDto("John", "okafor", "john@gmail.com", "JSS3", teacherRegDtoList.get(1))
        );

        for (StudentRegDto t : studentRegDtoList) {
            studentService.StudentRegistration(t);
        }


        List<CourseDto> courseDtos = List.of(
                new CourseDto("Mathematics"),
                new CourseDto("English Language"),
                new CourseDto("Yoruba"),
                new CourseDto("French"),
                new CourseDto("Geography"),
                new CourseDto("Basic Science"),
                new CourseDto("Basic Information"),
                new CourseDto("Literature")
        );
        for (CourseDto t : courseDtos) {
            courseService.AddCourse(t);
        }


    }


}
