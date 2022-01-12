package com.example.userservice.cotroller;

import com.example.userservice.vo.Greeting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {
    //private final Environment env;
    private final Greeting greeting;

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
}
