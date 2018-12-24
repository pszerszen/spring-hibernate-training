package com.osa.training.mapper;

import com.osa.training.model.dto.AbstractDTO;
import com.osa.training.model.persistance.AbstractEntity;

public abstract class AbstractMapper<Entity extends AbstractEntity, DTO extends AbstractDTO> {

    public Entity map(DTO dto) {
        Entity entity = toEntity(dto);
        passBasicValues(dto, entity);

        return entity;
    }

    public DTO map(Entity entity) {
        DTO dto = toDto(entity);
        passBasicValues(entity, dto);

        return dto;
    }

    abstract Entity toEntity(DTO dto);

    abstract DTO toDto(Entity entity);

    private static void passBasicValues(AbstractEntity entity, AbstractDTO dto) {
        entity.setId(dto.getId());
        entity.setCreateDate(dto.getCreateDate());
        entity.setUpdateDate(dto.getUpdateDate());
        entity.setVersion(dto.getVersion());
    }

    private static void passBasicValues(AbstractDTO dto, AbstractEntity entity) {
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setVersion(entity.getVersion());
    }
}
