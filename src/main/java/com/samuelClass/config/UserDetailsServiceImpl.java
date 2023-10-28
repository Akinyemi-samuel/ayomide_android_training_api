package com.samuelClass.config;

import com.samuelClass.dto.request.AuthenticationDto;
import com.samuelClass.repository.AdminRepository;
import com.samuelClass.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByEmail(username).map(u -> AuthenticationDto.builder()
                .email(u.getEmail())
                .password(u.getPassword())
                .role(u.getRole())
                .build()).orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
    }
}
