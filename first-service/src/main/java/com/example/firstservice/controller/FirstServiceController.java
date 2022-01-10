package com.example.firstservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/first-service")
public class FirstServiceController {
    @GetMapping("/welcom")
    public String welcom() {
        return "Welcom to the First Service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header){
      log.info(header);
      return "Hello World in First Service";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there first";
    }

}
