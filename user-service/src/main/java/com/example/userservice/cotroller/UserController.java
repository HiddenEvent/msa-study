package com.example.userservice.cotroller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {
    //private final Environment env;
    private final Greeting greeting;
    private final UserService userService;

    @GetMapping("/health-check")
    public String status() {
        return "health, Cheak Ok?";
    }
    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
      //  return env.getProperty("greeting.message");
      //  return "health, Cheak Ok?";
    }
    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody UserDto.Req reqDto) {
        UserDto.Resp respDto = userService.createUser(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respDto);
    }

}
