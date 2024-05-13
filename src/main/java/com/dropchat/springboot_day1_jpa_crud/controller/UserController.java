package com.dropchat.springboot_day1_jpa_crud.controller;

import com.dropchat.springboot_day1_jpa_crud.dto.request.ApiResponse;
import com.dropchat.springboot_day1_jpa_crud.dto.request.UserCreationRequest;
import com.dropchat.springboot_day1_jpa_crud.dto.request.UserUpdateRequest;
import com.dropchat.springboot_day1_jpa_crud.dto.response.UserResponse;
import com.dropchat.springboot_day1_jpa_crud.entity.User;
import com.dropchat.springboot_day1_jpa_crud.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserController {

    private UserService userService;

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){

        ApiResponse<User> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable(name = "userId") String id){
        return userService.getUser(id);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable(name = "userId") String id,@RequestBody UserUpdateRequest request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable(name = "userId") String id){
        userService.deleteUser(id);
        return "User has been deleted";
    }

}
