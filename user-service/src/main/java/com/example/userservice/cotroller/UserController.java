package com.example.userservice.cotroller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user-service")
public class UserController {
    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;

    @GetMapping("/health-check")
    public String status() {
        return String.format("health, Cheak Ok? UserService PORT : %s", env.getProperty("local.server.port"));
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

    @GetMapping("/users")
    public ResponseEntity<List<UserDto.Resp>> getUsers() {
        List<UserDto.Resp> respDtos = userService.getUserByAll();
        return ResponseEntity.status(HttpStatus.OK).body(respDtos);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto.Resp> getUser(@PathVariable String userId) {
        UserDto.Resp respDto = userService.getUserByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(respDto);
    }

}
