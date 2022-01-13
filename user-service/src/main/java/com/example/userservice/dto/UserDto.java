package com.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createAt;
    private String encryptedPwd;

    @Data
    public static class Req {
        @NotNull(message = "Email cannot be null")
        @Size(min = 2, message = "Email 2 글자이상 ")
        @Email
        private String email;

        @NotNull(message = "Name cannot be null")
        @Size(min = 2, message = "name 2 글자이상")
        private String name;

        @NotNull(message = "Password cannot be null")
        @Size(min = 8, message = "Password 8글자 이상")
        private String pwd;
    }
    @Data
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Resp{
        private String email;
        private String name;
        private String userId;

        private List<OrderDto.Resp> orders;
    }


}
