package com.dropchat.springboot_day1_jpa_crud.service;

import com.dropchat.springboot_day1_jpa_crud.dto.request.UserCreationRequest;
import com.dropchat.springboot_day1_jpa_crud.dto.request.UserUpdateRequest;
import com.dropchat.springboot_day1_jpa_crud.dto.response.UserResponse;
import com.dropchat.springboot_day1_jpa_crud.entity.User;
import com.dropchat.springboot_day1_jpa_crud.enums.Role;
import com.dropchat.springboot_day1_jpa_crud.exception.AppException;
import com.dropchat.springboot_day1_jpa_crud.exception.ErrorCode;
import com.dropchat.springboot_day1_jpa_crud.mapper.UserMapper;
import com.dropchat.springboot_day1_jpa_crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not Found"));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

}
