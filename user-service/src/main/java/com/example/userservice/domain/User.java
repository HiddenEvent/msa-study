package com.example.userservice.domain;

import com.example.userservice.dto.UserDto;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, unique = true)
    private String encryptedPwd;

    public static User createUser(UserDto reqDto) {
        return User.builder()
                .email(reqDto.getEmail())
                .name(reqDto.getName())
                .userId(reqDto.getUserId())
                .encryptedPwd(reqDto.getEncryptedPwd())
                .build();
    }

    public void setEncryptedPwd(String encryptedPwd) {
        this.encryptedPwd = encryptedPwd;
    }
}
