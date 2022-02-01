package com.example.orderservice.domain;

import com.example.orderservice.dto.OrderDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 336630427471984849L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, unique = true)
    private String orderId;


    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createAt;

    public static Order createOder(OrderDto reqDto) {
        return Order.builder()
                .productId(reqDto.getProductId())
                .qty(reqDto.getQty())
                .unitPrice(reqDto.getUnitPrice())
                .totalPrice(reqDto.getTotalPrice())
                .userId(reqDto.getUserId())
                .orderId(reqDto.getOrderId())
                .build();
    }
//
//    public void setEncryptedPwd(String encryptedPwd) {
//        this.encryptedPwd = encryptedPwd;
//    }
}
