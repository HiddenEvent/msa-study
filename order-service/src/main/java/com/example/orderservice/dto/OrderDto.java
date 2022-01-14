package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDto implements Serializable {
    private static final long serialVersionUID = 6867923581673925251L;

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String userId;
    private String orderId;
    @Data
    public static class Req {
        private String productId;
        private Integer qty;
        private Integer unitPrice;
        private Integer totalPrice;

        private String userId;
        private String orderId;
//        @NotNull(message = "Email cannot be null")
//        @Size(min = 2, message = "Email 2 글자이상 ")
//        @Email
//        private String email;
//
//        @NotNull(message = "Name cannot be null")
//        @Size(min = 2, message = "name 2 글자이상")
//        private String name;
//
//        @NotNull(message = "Password cannot be null")
//        @Size(min = 8, message = "Password 8글자 이상")
//        private String pwd;
    }
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Resp {
        private String productId;
        private Integer qty;
        private Integer unitPrice;
        private Integer totalPrice;

        private String userId;
        private String orderId;
        private Date createAt;
    }
}
