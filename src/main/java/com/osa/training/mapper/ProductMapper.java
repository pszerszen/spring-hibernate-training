package com.osa.training.mapper;

import com.osa.training.model.dto.ProductDTO;
import com.osa.training.model.persistance.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends AbstractMapper<ProductEntity, ProductDTO> {

    @Override
    ProductEntity toEntity(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();

        entity.setMarge(dto.getMarge());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setWeight(dto.getWeight());

        return entity;
    }

    @Override
    ProductDTO toDto(ProductEntity entity) {
        return ProductDTO.builder()
                .marge(entity.getMarge())
                .name(entity.getName())
                .price(entity.getPrice())
                .weight(entity.getWeight())
                .build();
    }
}
