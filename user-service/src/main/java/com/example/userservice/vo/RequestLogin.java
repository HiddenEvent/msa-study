package com.example.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin {
    @NotNull(message = "Emaill 미입력")
    @Size(min = 2, message = "Email을 제대로 입력해주세요")
    @Email
    private String email;
    @NotNull(message = "Password 미입력")
    @Size(min = 8, message = "Password를 제대로 입력해주세요")
    private String password;
}
