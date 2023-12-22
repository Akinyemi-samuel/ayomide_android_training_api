package com.samuelClass.controllers;

import com.samuelClass.dto.request.AuthenticationDto;
import com.samuelClass.dto.request.ChangePasswordRequest;
import com.samuelClass.dto.request.RegistrationDto;
import com.samuelClass.dto.response.AuthenticationResponse;
import com.samuelClass.model.Admin;
import com.samuelClass.services.AdminService;
import io.jsonwebtoken.MalformedJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Tag(name = "Admin")
public class AdminController {

    private final AdminService adminService;

    @Operation(
            summary = "Create a new Admin"
    )
    @ApiResponse(responseCode = "201", description = "created Admin")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AuthenticationResponse userRegistration(@RequestBody RegistrationDto registrationDto) {
        log.info("AdminController registers Admin: {}", registrationDto.email);
        return adminService.UserRegistration(registrationDto);
    }


    @Operation(
            summary = "Log in an Admin"
    )
    @ApiResponse(responseCode = "200", description = "created Admin")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public AuthenticationResponse userAuthentication(@RequestBody AuthenticationDto authenticationDto) {
        log.info("AdminController authenticates Admin: {}", authenticationDto.getEmail());
        return adminService.login(authenticationDto);
    }


    @Operation(
            summary = "delete an Admin"
    )
    @ApiResponse(responseCode = "200", description = "delete an Admin")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        log.info("AdminController deletes Admin record: {}", id);
        adminService.deleteAdmin(id);
    }



    @Operation(
            summary = "get all Admin"
    )
    @ApiResponse(responseCode = "200", description = "get all Admin")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Admin> getAdmin() {
        log.info("AdminController gets all Admin: {}");
        return adminService.getAdmin();
    }


    @GetMapping("/userdetails")
    public ResponseEntity<Admin> getUserDetailsByToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ((authentication == null || !authentication.isAuthenticated())) {
            throw new MalformedJwtException("User is not authenticated");
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findUserByUserName(username));
        }
    }


    @Operation(
            summary = "Change Admin Password"
    )
    @ApiResponse(responseCode = "200", description = "Admin Password updated")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        log.info("AdminController updates Admin details: {}");
        return adminService.updateAdminPassword(changePasswordRequest);

    }

}



