package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.OrderDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

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
        ArrayList<OrderDto.Resp> respOrderDtos = new ArrayList<>();
        respDto.setOrders(respOrderDtos);

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        /* 스프링 시큐리티 User 객체리턴을 해줘야함*/
        return new org.springframework.security.core.userdetails.User(
                user.getEmail()
                ,user.getEncryptedPwd()
                ,true ,true ,true ,true
                , new ArrayList<>()
        );
    }
}
