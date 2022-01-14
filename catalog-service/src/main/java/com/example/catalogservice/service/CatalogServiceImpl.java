package com.example.catalogservice.service;

import com.example.catalogservice.domain.Catalog;
import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;
    private final ModelMapper mapper;

    @Override
    public List<CatalogDto.Resp> getAllCatalogs() {

        List<Catalog> catalogs = catalogRepository.findAll();
        ArrayList<CatalogDto.Resp> respDtos = new ArrayList<>();
        for (Catalog catalog : catalogs) {
            respDtos.add(mapper.map(catalog, CatalogDto.Resp.class));
        }
        return respDtos;
    }
}
