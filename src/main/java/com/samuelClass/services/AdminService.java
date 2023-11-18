package com.samuelClass.services;

import com.samuelClass.config.JwtService;
import com.samuelClass.dto.request.AuthenticationDto;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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


    public String UserRegistration(RegistrationDto registrationDto) {

        if (!isEmailValid.test(registrationDto.email()))
            throw new ApiException("Invalid Email Found", HttpStatus.NOT_ACCEPTABLE);

        if (!isPasswordValid.test(registrationDto.password()))
            throw new ApiException("Password is Invalid", HttpStatus.NOT_ACCEPTABLE);

        Optional<Admin> userOptional = adminRepository.findByEmail(registrationDto.email());
        if (userOptional.isPresent()) {
            throw new ApiException("User Already Exists", HttpStatus.NOT_FOUND);
        }

        Admin teacher = Admin.builder()
                .firstName(registrationDto.firstName())
                .lastName(registrationDto.lastName())
                .role(Role.valueOf("ADMIN"))
                .email(registrationDto.email())
                .password(passwordEncoder.encode(registrationDto.password()) )
                .build();

        adminRepository.save(teacher);
        return "Registration successful!";

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
