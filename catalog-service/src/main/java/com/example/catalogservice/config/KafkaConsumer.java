package com.example.catalogservice.config;

import com.example.catalogservice.domain.Catalog;
import com.example.catalogservice.repository.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final CatalogRepository repository;

    /*{example-catalog-topic} 토픽의 내용을 가져와 아래 메서드로 실행 한다.*/
    @KafkaListener(topics = "example-catalog-topic")
    public void updateQty(String kafkaMessage) {
        log.info("Kafka Message 받기 성공 ===>" + kafkaMessage);
        // 받아온 메시지 역직렬화 과정
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        Catalog entity = repository.findByProductId(String.valueOf(map.get("productId")));
        if (entity != null) {
            entity.sellStock((Integer) map.get("qty"));
            repository.save(entity);
        }

    }
}
