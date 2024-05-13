package com.dropchat.springboot_day1_jpa_crud.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {

    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;


}
