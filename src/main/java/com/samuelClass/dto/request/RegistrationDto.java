package com.samuelClass.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RegistrationDto {

    public String firstName;
    public String lastName;
    public String email;
    public String role;
    public String password;
}
