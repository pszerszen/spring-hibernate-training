package com.osa.training.model.dto;

import com.osa.training.model.persistance.Status;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class PurchaseDTO extends AbstractDTO {

    @NotNull
    private Status status;

    @NotNull
    private List<OrderProductDTO> orders;

    @Builder
    public PurchaseDTO(Long id,
                       Long version,
                       LocalDateTime createDate,
                       LocalDateTime updateDate,
                       @NotNull Status status,
                       @NotNull List<OrderProductDTO> orders) {
        super(id, version, createDate, updateDate);
        this.status = status;
        this.orders = orders;
    }
}
