package com.osa.training.validator.impl;

import com.osa.training.model.dto.OrderProductDTO;
import com.osa.training.model.dto.ProductDTO;
import com.osa.training.model.dto.PurchaseDTO;
import com.osa.training.validator.Validator;
import com.osa.training.validator.exception.InvalidOrderWeightException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.osa.training.validator.impl.OrderWeightValidator.MAX_ORDER_WEIGHT;
import static com.osa.training.validator.impl.ValidatorTestHelper.failIfInvalid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static java.util.Collections.singletonList;

class OrderWeightValidatorTest {

    private Validator validator = new OrderWeightValidator();

    @Test
    void shouldPassForLightProduct() {
        failIfInvalid(validator, null,
                PurchaseDTO.builder()
                        .orders(singletonList(OrderProductDTO.builder()
                                .amount(1)
                                .product(ProductDTO.builder()
                                        .weight(BigDecimal.valueOf(1))
                                        .build())
                                .build()))
                        .build());
    }

    @Test
    void shouldFailForOneHeavyProduct() {
        assertThrows(InvalidOrderWeightException.class, () -> validator.validate(null,
                PurchaseDTO.builder()
                        .orders(singletonList(OrderProductDTO.builder()
                                .amount(1)
                                .product(ProductDTO.builder()
                                        .weight(BigDecimal.valueOf(1))
                                        .build())
                                .product(ProductDTO.builder()
                                        .weight(BigDecimal.valueOf(MAX_ORDER_WEIGHT + 1))
                                        .build())
                                .build()))
                        .build()));
    }

    @Test
    void shouldFailForManyHeavyProducts() {
        assertThrows(InvalidOrderWeightException.class, () -> validator.validate(null,
                PurchaseDTO.builder()
                        .orders(singletonList(OrderProductDTO.builder()
                                .amount(1)
                                .product(ProductDTO.builder()
                                        .weight(BigDecimal.valueOf(MAX_ORDER_WEIGHT + 1))
                                        .build())
                                .product(ProductDTO.builder()
                                        .weight(BigDecimal.valueOf(MAX_ORDER_WEIGHT + 1))
                                        .build())
                                .build()))
                        .build()));
    }
}
