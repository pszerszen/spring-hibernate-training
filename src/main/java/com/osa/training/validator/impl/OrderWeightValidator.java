package com.osa.training.validator.impl;

import com.osa.training.model.dto.CustomerDTO;
import com.osa.training.model.dto.OrderProductDTO;
import com.osa.training.model.dto.PurchaseDTO;
import com.osa.training.validator.Validator;
import com.osa.training.validator.exception.InvalidOrderWeightException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderWeightValidator implements Validator {

    static final int MAX_ORDER_WEIGHT = 25;

    @Override
    public void validate(CustomerDTO customer, PurchaseDTO purchase) {
        boolean invalidPurchase = purchase.getOrders().stream().anyMatch(this::isOrderWeightTooBig);

        if(invalidPurchase) {
            throw new InvalidOrderWeightException();
        }
    }

    private boolean isOrderWeightTooBig(OrderProductDTO order) {
        return getOrderWeight(order).doubleValue() > MAX_ORDER_WEIGHT;
    }

    private BigDecimal getOrderWeight(OrderProductDTO order) {
        return order.getProduct().getWeight()
                .multiply(BigDecimal.valueOf(order.getAmount()));
    }
}
