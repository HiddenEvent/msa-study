package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
/* 어떤 필드가 사용될 것이다. */
// 임의로 사용하는 것이다 Kafka를 사용하기 위해 필요한 필드 들이다.
@Data
@AllArgsConstructor
public class Field {
    private String type;
    private boolean optional;
    private String field;
}
