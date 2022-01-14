package com.example.catalogservice.cotroller;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog-service")
public class CatalogController {
    private final Environment env;
    private final CatalogService catalogService;

    @GetMapping("/health-check")
    public String status() {
        return String.format("health, Cheak Ok? UserService PORT : %s", env.getProperty("local.server.port"));
    }

//    @PostMapping("/users")
//    public ResponseEntity createUser(@RequestBody CatalogDto.Req reqDto) {
//        UserDto.Resp respDto = userService.createUser(reqDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(respDto);
//    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<CatalogDto.Resp>> getAllCatalogs() {
        List<CatalogDto.Resp> respDtos = catalogService.getAllCatalogs();
        return ResponseEntity.status(HttpStatus.OK).body(respDtos);
    }

//    @GetMapping("/users/{userId}")
//    public ResponseEntity<UserDto.Resp> getUser(@PathVariable String userId) {
//        UserDto.Resp respDto = userService.getUserByUserId(userId);
//        return ResponseEntity.status(HttpStatus.OK).body(respDto);
//    }

}
