package com.osa.training.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends AbstractDTO {

    @NotNull
    private final String name;
    @NotNull
    @Size(max = 100)
    private final BigDecimal marge;
    @Size(max = 1000)
    @NotNull
    private final BigDecimal price;
    @NotNull
    @Size(max = 25)
    private final BigDecimal weight;

    @Builder
    public ProductDTO(Long id,
                      Long version,
                      LocalDateTime createDate,
                      LocalDateTime updateDate,
                      @NotNull String name,
                      @NotNull @Size(max = 100) BigDecimal marge,
                      @Size(max = 1000) @NotNull BigDecimal price,
                      @NotNull @Size(max = 25) BigDecimal weight) {
        super(id, version, createDate, updateDate);
        this.name = name;
        this.marge = marge;
        this.price = price;
        this.weight = weight;
    }
}
