package com.example.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
public class CatalogDto implements Serializable {
    private static final long serialVersionUID = 871550336167510921L;

    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;

    private String orderId;
    private String userId;
    @Data
    public static class Req {
        private String productId;
        private String productName;
        private Integer stock;
        private Integer unitPrice;

        private String orderId;
        private String userId;
//        @NotNull(message = "Email cannot be null")
//        @Size(min = 2, message = "Email 2 글자이상 ")
//        @Email
//        private String email;
//
//        @NotNull(message = "Name cannot be null")
//        @Size(min = 2, message = "name 2 글자이상")
//        private String name;
//
//        @NotNull(message = "Password cannot be null")
//        @Size(min = 8, message = "Password 8글자 이상")
//        private String pwd;
    }
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Resp {
        private String productId;
        private String productName;
        private Integer unitPrice;
        private Integer totalPrice;
        private String orderId;
        private Date createAt;
    }
}
