package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.domain.User;
import com.example.userservice.dto.OrderDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    private final Environment env;
    private final RestTemplate restTemplate;
    private final OrderServiceClient orderServiceClient;

    private final CircuitBreakerFactory circuitBreakerFactory;

    @Override
    public UserDto.Resp createUser(UserDto.Req reqDto) {
        //MatchingStrategies.STRICT = 모든 속성이 동일해야 class 매핑 가능함
        UserDto dto = mapper.map(reqDto, UserDto.class);
        String encode = passwordEncoder.encode(reqDto.getPwd());
        dto.setUserId(UUID.randomUUID().toString());
        dto.setEncryptedPwd(encode);

        User user = User.createUser(dto);
        userRepository.save(user);
        return mapper.map(user, UserDto.Resp.class);
    }

    @Override
    public UserDto.Resp getUserByUserId(String userId) {

        User user = userRepository.findByUserId(userId);
        if (user == null) return null;

        UserDto.Resp respDto = mapper.map(user, UserDto.Resp.class);
        /*  //RestTemplate 사용했던 내용
        String orderUrl = String.format(env.getProperty("order_service.url"), userId);
        ResponseEntity<List<OrderDto.Resp>> orderResp =
                restTemplate.exchange(orderUrl, HttpMethod.GET, null
                        , new ParameterizedTypeReference<List<OrderDto.Resp>>() {
                        });
        List<OrderDto.Resp> respOrderDtos = orderResp.getBody();
        respDto.setOrders(respOrderDtos);
        */
        /* FeignClient 사용하여 마이크로 서비스 호출 */
//        List<OrderDto.Resp> orders = null;
//        try {
//            orders = orderServiceClient.getOrders(userId);
//        } catch (FeignException e) {
//            log.error(e.getMessage());
//        }
        /* ErrorDecoder */
//        List<OrderDto.Resp> orders = orderServiceClient.getOrders(userId);
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
        List<OrderDto.Resp> orders = circuitBreaker.run(() -> orderServiceClient.getOrders(userId), throwable -> new ArrayList<>());
        respDto.setOrders(orders);

        return respDto;
    }

    @Override
    public List<UserDto.Resp> getUserByAll() {
        List<User> users = userRepository.findAll();
        ArrayList<UserDto.Resp> respDtos = new ArrayList<>();
        for (User user : users) {
            respDtos.add(mapper.map(user, UserDto.Resp.class));
        }
        return respDtos;
    }

    /* 로그인 순서 -3 */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        /* 스프링 시큐리티 User 객체리턴을 해줘야함*/
        return new org.springframework.security.core.userdetails.User(
                user.getEmail()
                , user.getEncryptedPwd()
                , true, true, true, true
                , new ArrayList<>()
        );
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }
}
