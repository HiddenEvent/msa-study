package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto.Resp createUser(UserDto.Req reqDto);
    UserDto.Resp getUserByUserId(String userId);
    List<UserDto.Resp> getUserByAll();
}
