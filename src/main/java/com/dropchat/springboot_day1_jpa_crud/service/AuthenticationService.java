package com.dropchat.springboot_day1_jpa_crud.service;

import com.dropchat.springboot_day1_jpa_crud.dto.request.AuthenticationRequest;
import com.dropchat.springboot_day1_jpa_crud.exception.AppException;
import com.dropchat.springboot_day1_jpa_crud.exception.ErrorCode;
import com.dropchat.springboot_day1_jpa_crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class AuthenticationService {

    private UserRepository userRepository;
    public boolean isAuthenticated(AuthenticationRequest request){
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }

}
