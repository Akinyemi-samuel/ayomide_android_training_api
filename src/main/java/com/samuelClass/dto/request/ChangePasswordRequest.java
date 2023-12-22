package com.samuelClass.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ChangePasswordRequest {

    private final String oldPassword;

    private final String newPassword;

}
