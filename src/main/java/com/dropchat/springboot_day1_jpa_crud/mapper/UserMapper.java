package com.dropchat.springboot_day1_jpa_crud.mapper;

import com.dropchat.springboot_day1_jpa_crud.dto.request.UserCreationRequest;
import com.dropchat.springboot_day1_jpa_crud.dto.request.UserUpdateRequest;
import com.dropchat.springboot_day1_jpa_crud.dto.response.UserResponse;
import com.dropchat.springboot_day1_jpa_crud.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
