package com.samuelClass.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public String role;

    public String password;
}
