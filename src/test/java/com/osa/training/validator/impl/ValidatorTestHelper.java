package com.osa.training.validator.impl;

import com.osa.training.model.dto.CustomerDTO;
import com.osa.training.model.dto.OrderProductDTO;
import com.osa.training.model.dto.ProductDTO;
import com.osa.training.model.dto.PurchaseDTO;
import com.osa.training.model.persistance.Status;
import com.osa.training.validator.Validator;
import net.bytebuddy.utility.RandomString;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;

import static java.util.Collections.singletonList;

class ValidatorTestHelper {
    static PurchaseDTO mockPurchaseWithStatus(Status status) {
        return PurchaseDTO.builder()
                .orders(singletonList(OrderProductDTO.builder()
                        .amount(1)
                        .product(ProductDTO.builder()
                                .name(RandomString.make())
                                .build())
                        .build()))
                .status(status)
                .build();
    }

    static PurchaseDTO mockLowValuePurchase() {
        return PurchaseDTO.builder()
                .orders(singletonList(OrderProductDTO.builder()
                        .amount(1)
                        .product(ProductDTO.builder()
                                .price(BigDecimal.valueOf(100))
                                .marge(BigDecimal.valueOf(23))
                                .build())
                        .build()))
                .build();
    }

    static PurchaseDTO mockHighValuePurchase() {
        return PurchaseDTO.builder()
                .orders(singletonList(OrderProductDTO.builder()
                        .amount(5)
                        .product(ProductDTO.builder()
                                .price(BigDecimal.valueOf(1000))
                                .marge(BigDecimal.valueOf(23))
                                .build())
                        .build()))
                .build();
    }

    static PurchaseDTO mockPurchaseForProductWithValue(double price, double marge, int amount) {
        return PurchaseDTO.builder()
                .orders(singletonList(OrderProductDTO.builder()
                        .amount(amount)
                        .product(ProductDTO.builder()
                                .name(RandomString.make())
                                .price(BigDecimal.valueOf(price))
                                .marge(BigDecimal.valueOf(marge))
                                .build())
                        .build()))
                .build();
    }

    static void failIfInvalid(Validator validator, CustomerDTO customer, PurchaseDTO purchase) {
        try {
            validator.validate(customer, purchase);
        } catch (Exception e) {
            fail();
        }
    }
}
