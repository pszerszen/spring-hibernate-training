package com.osa.training.validator.impl;

import com.osa.training.validator.Validator;
import com.osa.training.validator.exception.InvalidPurchaseException;
import org.junit.jupiter.api.Test;

import static com.osa.training.validator.impl.CargoValueValidator.MAX_AMOUNT_OF_VALUABLE_PRODUCTS;
import static com.osa.training.validator.impl.CargoValueValidator.VALUABLE_PRODUCT_PRICE_THRESHOLD;
import static com.osa.training.validator.impl.ValidatorTestHelper.failIfInvalid;
import static com.osa.training.validator.impl.ValidatorTestHelper.mockPurchaseForProductWithValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CargoValueValidatorTest {

    private Validator validator = new CargoValueValidator();

    @Test
    void shouldPassForSmallAmountOfCheapProducts() {
        failIfInvalid(validator, null,
                mockPurchaseForProductWithValue(VALUABLE_PRODUCT_PRICE_THRESHOLD, 23, MAX_AMOUNT_OF_VALUABLE_PRODUCTS));
    }

    @Test
    void shouldPassForBigAmountOfCheapProducts() {
        failIfInvalid(validator, null,
                mockPurchaseForProductWithValue(VALUABLE_PRODUCT_PRICE_THRESHOLD, 23, MAX_AMOUNT_OF_VALUABLE_PRODUCTS + 1));
    }

    @Test
    void shouldPassForSmallAmountOfExpensiveProducts() {
        failIfInvalid(validator, null,
                mockPurchaseForProductWithValue(VALUABLE_PRODUCT_PRICE_THRESHOLD + 1, 23, MAX_AMOUNT_OF_VALUABLE_PRODUCTS));
    }

    @Test
    void shouldFailForBigAmountOfExpensiveProducts() {
        assertThrows(InvalidPurchaseException.class, () -> validator.validate(
                null,
                mockPurchaseForProductWithValue(
                        VALUABLE_PRODUCT_PRICE_THRESHOLD + 1,
                        23,
                        MAX_AMOUNT_OF_VALUABLE_PRODUCTS + 1)));
    }
}
