package com.osa.training.model.persistance;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
public class ProductEntity extends AbstractEntity {

    @Column
    private String name;

    @Column
    private BigDecimal marge;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal weight;
}
