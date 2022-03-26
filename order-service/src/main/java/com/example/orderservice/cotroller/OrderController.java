package com.example.orderservice.cotroller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.messagequeue.connector.OrderProducer;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/order-service")
public class OrderController {
    private final Environment env;
    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;

    @GetMapping("/health-check")
    public String status() {
        return String.format("health, Cheak Ok? OrderService PORT : %s", env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity createOrder(@PathVariable String userId, @RequestBody OrderDto.Req reqDto) {
        log.info("Before added Order data");
        reqDto.setUserId(userId);
        reqDto.setOrderId(UUID.randomUUID().toString());
        reqDto.setTotalPrice(reqDto.getUnitPrice() * reqDto.getQty());
        /* JPA : KafkaConnect를 사용하여 DB에 바로 넣기 때문에 JPA를 사용하지 않아도된다. */
        OrderDto.Resp respDto = orderService.createOrder(reqDto);

        /* Kafka Topic만 사용해 메세지 처리*/
//        kafkaProducer.send("example-catalog-topic", reqDto);
        /* Kafka connect로 메세지 처리*/
//        orderProducer.send("orders", reqDto);
        log.info("After added Order data");

        return ResponseEntity.status(HttpStatus.CREATED).body(respDto);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderDto.Resp>> getOrdersByUserId(@PathVariable String userId) throws Exception {
        log.info("Before retrieve Order data");
        List<OrderDto.Resp> respDtos = orderService.getOrdersByUserId(userId);
        try {
            Thread.sleep(1000);
            throw new Exception("장애 발생");
        } catch (InterruptedException ex){
            log.error(ex.getMessage());
        }

        log.info("After retrieve Order data");
        return ResponseEntity.status(HttpStatus.OK).body(respDtos);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDto.Resp> getOrderByOrderId(@PathVariable String orderId) {
        OrderDto.Resp respDto = orderService.getOrderByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(respDto);
    }

}
