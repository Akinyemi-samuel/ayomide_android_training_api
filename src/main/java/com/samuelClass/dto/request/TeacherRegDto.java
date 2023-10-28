package com.samuelClass.dto.request;

import lombok.Builder;

@Builder
public record TeacherRegDto(
        String firstName,
        String lastName,
        String email,
        String role,
        String level,
        String password
) {
}
