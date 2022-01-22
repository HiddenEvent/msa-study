package com.example.userservice.client;

import com.example.userservice.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service") /* 마이크로서비스 name 입력*/
public interface OrderServiceClient {

    @GetMapping("/order-service/{userId}/orders-ng") /* 호출하려는 마이크로 서비스 동일하게*/
    List<OrderDto.Resp> getOrders(@PathVariable(value = "userId") String userId);
}
