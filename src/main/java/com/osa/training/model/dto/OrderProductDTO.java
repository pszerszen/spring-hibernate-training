package com.osa.training.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class OrderProductDTO extends AbstractDTO {

    @NotNull
    private Integer amount;
    @NotNull
    private ProductDTO product;

    @Builder
    public OrderProductDTO(Long id,
                           Long version,
                           LocalDateTime createDate,
                           LocalDateTime updateDate,
                           @NotNull Integer amount,
                           @NotNull ProductDTO product) {
        super(id, version, createDate, updateDate);
        this.amount = amount;
        this.product = product;
    }
}

