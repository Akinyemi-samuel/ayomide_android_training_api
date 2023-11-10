package com.samuelClass.controllers;

import com.samuelClass.dto.request.AuthenticationDto;
import com.samuelClass.dto.request.RegistrationDto;
import com.samuelClass.dto.response.AuthenticationResponse;
import com.samuelClass.services.AdminService;
import com.samuelClass.services.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @Operation(
            summary = "Create a new Admin"
    )
    @ApiResponse(responseCode = "201", description = "created Admin")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String userRegistration(@RequestBody RegistrationDto registrationDto){
        log.info("TeacherController registers teachers: {}", registrationDto.email());
        return adminService.UserRegistration(registrationDto);
    }


    @Operation(
            summary = "Log in an Admin"
    )
    @ApiResponse(responseCode = "200", description = "created Admin")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public AuthenticationResponse userAuthentication(@RequestBody AuthenticationDto authenticationDto){
        return adminService.login(authenticationDto);
    }

    @Operation(
            summary = "delete an Admin"
    )
    @ApiResponse(responseCode = "200", description = "delete an Admin")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id){
         adminService.deleteAdmin(id);
    }

    @Operation(
            summary = "get all Admin"
    )
    @ApiResponse(responseCode = "200", description = "get all Admin")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public void getAdmin(){
        adminService.getAdmin();
    }
}
