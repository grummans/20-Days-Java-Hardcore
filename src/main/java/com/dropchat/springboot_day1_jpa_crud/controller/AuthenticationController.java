package com.dropchat.springboot_day1_jpa_crud.controller;

import com.dropchat.springboot_day1_jpa_crud.dto.request.ApiResponse;
import com.dropchat.springboot_day1_jpa_crud.dto.request.AuthenticationRequest;
import com.dropchat.springboot_day1_jpa_crud.dto.response.AuthenticationResponse;
import com.dropchat.springboot_day1_jpa_crud.service.AuthenticationService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Builder
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        boolean result = authenticationService.isAuthenticated(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .authenticated(result)
                        .build())
                .build();
    }

}
