package com.osa.training.model.persistance;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "purchase")
public class PurchaseEntity extends AbstractEntity {
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    private List<OrderProductEntity> orders = new ArrayList<>();

}
