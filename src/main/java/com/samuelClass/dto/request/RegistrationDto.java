package com.samuelClass.dto.request;

import lombok.Builder;

@Builder
public class RegistrationDto {

    public String firstName;
    public String lastName;
    public String email;
    public String role;
    public String password;
}
