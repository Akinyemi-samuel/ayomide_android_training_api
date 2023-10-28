package com.samuelClass.dto.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(String token) {

}
