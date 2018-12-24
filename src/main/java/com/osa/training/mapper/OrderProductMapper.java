package com.osa.training.mapper;

import com.osa.training.model.dto.OrderProductDTO;
import com.osa.training.model.persistance.OrderProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderProductMapper extends AbstractMapper<OrderProductEntity, OrderProductDTO> {

    private final ProductMapper productMapper;

    @Override
    OrderProductEntity toEntity(OrderProductDTO dto) {
        OrderProductEntity entity = new OrderProductEntity();

        entity.setAmount(dto.getAmount());
        entity.setProduct(productMapper.toEntity(dto.getProduct()));

        return entity;
    }

    @Override
    OrderProductDTO toDto(OrderProductEntity entity) {
        return OrderProductDTO.builder()
                .amount(entity.getAmount())
                .product(productMapper.toDto(entity.getProduct()))
                .build();
    }
}
