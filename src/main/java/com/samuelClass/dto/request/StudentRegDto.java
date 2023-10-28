package com.samuelClass.dto.request;

import com.samuelClass.model.Courses;
import com.samuelClass.model.Teacher;
import lombok.Builder;

import java.util.List;

@Builder
public record StudentRegDto(
        String firstName,
        String lastName,
        String email,
        String level,
        List<Courses> Courses,
        Teacher teacher



) {
    public StudentRegDto(String firstName, String lastName, String email, String level, Teacher teacher) {
        this(firstName, lastName, email, level, null, teacher);
    }
}
