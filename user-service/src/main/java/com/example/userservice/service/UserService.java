package com.example.userservice.service;

import com.example.userservice.dto.UserDto;

public interface UserService {
    UserDto.Resp createUser(UserDto.Req reqDto);
}
