package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto.Resp createUser(UserDto.Req reqDto);
    UserDto.Resp getUserByUserId(String userId);
    List<UserDto.Resp> getUserByAll();
}
