package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class KafkaOrderDto implements Serializable {
    private static final long serialVersionUID = -6229517023682691218L;

    private Schema schema;
    private Payload payload;

}
