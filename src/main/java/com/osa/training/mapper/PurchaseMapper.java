package com.osa.training.mapper;

import com.osa.training.model.dto.PurchaseDTO;
import com.osa.training.model.persistance.PurchaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PurchaseMapper extends AbstractMapper<PurchaseEntity, PurchaseDTO> {

    private final OrderProductMapper orderProductMapper;

    @Override
    PurchaseEntity toEntity(PurchaseDTO dto) {
        PurchaseEntity entity = new PurchaseEntity();

        entity.setStatus(dto.getStatus());
        entity.setOrders(dto.getOrders().stream()
                .map(orderProductMapper::toEntity)
                .collect(toList()));

        return entity;
    }

    @Override
    PurchaseDTO toDto(PurchaseEntity entity) {
        return PurchaseDTO.builder()
                .status(entity.getStatus())
                .orders(entity.getOrders().stream()
                        .map(orderProductMapper::toDto)
                        .collect(toList()))
                .build();
    }
}
