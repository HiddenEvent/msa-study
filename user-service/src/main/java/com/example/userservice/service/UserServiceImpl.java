package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserDto.Resp createUser(UserDto.Req reqDto) {
        //MatchingStrategies.STRICT = 모든 속성이 동일해야 class 매핑 가능함
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto dto = mapper.map(reqDto, UserDto.class);
        dto.setUserId(UUID.randomUUID().toString());
        dto.setEncryptedPwd("encrypted_password");
        User user = User.createUser(dto);
        userRepository.save(user);
        return mapper.map(user, UserDto.Resp.class);
    }
}
