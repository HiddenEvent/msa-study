package com.example.catalogservice.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "catalog")
public class Catalog implements Serializable {
    private static final long serialVersionUID = 336630427471984849L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120, unique = true)
    private String productId;
    @Column(nullable = false, length = 50)
    private String productName;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createAt;

//    public static Catalog createUser(UserDto reqDto) {
//        return Catalog.builder()
//                .email(reqDto.getEmail())
//                .name(reqDto.getName())
//                .userId(reqDto.getUserId())
//                .encryptedPwd(reqDto.getEncryptedPwd())
//                .build();
//    }
//
//    public void setEncryptedPwd(String encryptedPwd) {
//        this.encryptedPwd = encryptedPwd;
//    }
}
