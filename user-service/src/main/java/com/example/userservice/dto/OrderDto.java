package com.example.userservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createAt;
    private String orderId;
    @Data
    public static class Resp {
        private String productId;
        private Integer qty;
        private Integer unitPrice;
        private Integer totalPrice;
        private Date createAt;
        private String orderId;
    }
}
