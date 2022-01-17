package com.example.apigatwayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApigatwayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApigatwayServiceApplication.class, args);
    }

    @Bean
    public HttpTraceRepository httpTraceRepository() {
        /* 외부 호출 확인을 위한 추가 (Acturator 기능) */
        // 클라이언트가 요청했던 정보가 메모리에 담긴다.
        return new InMemoryHttpTraceRepository();
    }
}
