package com.dropchat.springboot_day1_jpa_crud.service;

import com.dropchat.springboot_day1_jpa_crud.dto.request.UserCreationRequest;
import com.dropchat.springboot_day1_jpa_crud.dto.request.UserUpdateRequest;
import com.dropchat.springboot_day1_jpa_crud.dto.response.UserResponse;
import com.dropchat.springboot_day1_jpa_crud.entity.User;
import com.dropchat.springboot_day1_jpa_crud.exception.AppException;
import com.dropchat.springboot_day1_jpa_crud.exception.ErrorCode;
import com.dropchat.springboot_day1_jpa_crud.mapper.UserMapper;
import com.dropchat.springboot_day1_jpa_crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public User createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        return userRepository.save(user);
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
