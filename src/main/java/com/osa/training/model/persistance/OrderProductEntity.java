package com.osa.training.model.persistance;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "order_product")
public class OrderProductEntity extends AbstractEntity {

    @Column
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "product")
    private ProductEntity product;
}
