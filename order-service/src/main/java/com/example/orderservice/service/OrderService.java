package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto.Resp createOrder(OrderDto.Req reqDto);
    OrderDto.Resp getOrderByOrderId(String orderId);
    List<OrderDto.Resp> getOrdersByUserId(String userId);

}
