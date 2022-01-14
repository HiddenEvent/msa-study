package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    @Override
    public OrderDto.Resp createOrder(OrderDto.Req reqDto) {
        OrderDto dto = mapper.map(reqDto, OrderDto.class);
        Order user = Order.createOder(dto);
        orderRepository.save(user);
        return mapper.map(user, OrderDto.Resp.class);
    }

    @Override
    public OrderDto.Resp getOrderByOrderId(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);

        return mapper.map(order, OrderDto.Resp.class);
    }

    @Override
    public List<OrderDto.Resp> getOrdersByUserId(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        List<OrderDto.Resp> respDtos = new ArrayList<>();
        for (Order order : orders) {
            respDtos.add(mapper.map(order, OrderDto.Resp.class));
        }
        return respDtos;
    }
}
