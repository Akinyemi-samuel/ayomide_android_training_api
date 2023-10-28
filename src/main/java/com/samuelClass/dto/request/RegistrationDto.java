package com.samuelClass.dto.request;

import lombok.Builder;

@Builder
public record RegistrationDto(
        String firstName,
        String lastName,
        String email,
        String role,
        String password
) {
}
