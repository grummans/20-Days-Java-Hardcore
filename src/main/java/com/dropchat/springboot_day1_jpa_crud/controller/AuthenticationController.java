package com.dropchat.springboot_day1_jpa_crud.controller;

import com.dropchat.springboot_day1_jpa_crud.dto.request.ApiResponse;
import com.dropchat.springboot_day1_jpa_crud.dto.request.AuthenticationRequest;
import com.dropchat.springboot_day1_jpa_crud.dto.request.IntrospectRequest;
import com.dropchat.springboot_day1_jpa_crud.dto.response.AuthenticationResponse;
import com.dropchat.springboot_day1_jpa_crud.dto.response.IntrospectResponse;
import com.dropchat.springboot_day1_jpa_crud.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Builder
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.isAuthenticated(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspectResponse(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

}
