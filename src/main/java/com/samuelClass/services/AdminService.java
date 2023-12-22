package com.samuelClass.services;

import com.samuelClass.config.JwtService;
import com.samuelClass.dto.request.AuthenticationDto;
import com.samuelClass.dto.request.ChangePasswordRequest;
import com.samuelClass.dto.request.RegistrationDto;
import com.samuelClass.dto.response.AuthenticationResponse;
import com.samuelClass.exception.ApiException;
import com.samuelClass.model.Admin;
import com.samuelClass.model.Role;
import com.samuelClass.model.Teacher;
import com.samuelClass.repository.AdminRepository;
import com.samuelClass.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {

    private final IsEmailValid isEmailValid;
    private final AdminRepository adminRepository;
    private final IsPasswordValid isPasswordValid;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationResponse login(AuthenticationDto authenticationDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.getEmail(),
                        authenticationDto.getPassword()
                )
        );

        if (!authentication.isAuthenticated()) throw new ApiException("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        else {
            // if user is authenticated, return a json web token
            AuthenticationDto authenticationDto1 = adminRepository.findByEmail(authenticationDto.getEmail()).map(u -> AuthenticationDto.builder()
                    .email(u.getEmail())
                    .password(u.getPassword())
                    .build()).get();
            var token = jwtService.generateToken(authenticationDto1);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();

        }

    }


    public AuthenticationResponse UserRegistration(RegistrationDto registrationDto) {

        if (!isEmailValid.test(registrationDto.email))
            throw new ApiException("Invalid Email Found", HttpStatus.NOT_ACCEPTABLE);

        if (!isPasswordValid.test(registrationDto.password))
            throw new ApiException("Password is Invalid", HttpStatus.NOT_ACCEPTABLE);

        Optional<Admin> userOptional = adminRepository.findByEmail(registrationDto.email);
        if (userOptional.isPresent()) {
            throw new ApiException("User Already Exists", HttpStatus.NOT_FOUND);
        }

        Admin teacher = Admin.builder()
                .firstName(registrationDto.firstName)
                .lastName(registrationDto.lastName)
                .role(Role.valueOf("ADMIN"))
                .email(registrationDto.email)
                .password(passwordEncoder.encode(registrationDto.password) )
                .build();

        adminRepository.save(teacher);
        return AuthenticationResponse.builder()
                .token("Registration successful!")
                .build();

    }


    public ResponseEntity<String> updateAdminPassword(ChangePasswordRequest changePasswordRequest){

        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().body("User not authenticated");
        }


        // Authenticate the user with the provided old password
        String username = authentication.getName();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, changePasswordRequest.getOldPassword());
        authenticationManager.authenticate(authenticationToken);

        // If authentication successful, update the password
        if (authenticationToken.isAuthenticated()) {
            // Fetch user by username
            Admin admin = findUserByUserName(username);

            if (admin != null) {
                String newPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
                admin.setPassword(newPassword);

                // Save the updated user with the new password
                adminRepository.save(admin);
                return ResponseEntity.ok("Password updated successfully");
            } else {
                return ResponseEntity.badRequest().body("User not found");
            }
        } else {
            return ResponseEntity.badRequest().body("Old password is incorrect");
        }

    }


    public void deleteAdmin(long id){
        adminRepository.deleteById(id);
    }

    public List<Admin> getAdmin(){
        return adminRepository.findAll();
    }


    public Admin findUserByUserName(String userName) {
        Optional<Admin> optional = adminRepository.findByEmail(userName);
        return optional.orElseThrow(()-> new ApiException ("User not found", HttpStatus.NOT_FOUND));
    }
}
