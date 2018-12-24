package com.osa.training.repo;

import com.osa.training.model.persistance.ProductEntity;
import com.osa.training.model.persistance.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PurchaseSearchCriteria {

    private Status status;
    private LocalDateTime from;
    private LocalDateTime to;
    private ProductEntity product;
    private double totalAmount;
}
