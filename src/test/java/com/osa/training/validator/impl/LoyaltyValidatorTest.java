package com.osa.training.validator.impl;

import com.google.common.collect.ImmutableSet;
import com.osa.training.model.dto.CustomerDTO;
import com.osa.training.model.dto.PurchaseDTO;
import com.osa.training.model.persistance.Status;
import com.osa.training.validator.Validator;
import com.osa.training.validator.exception.InvalidPurchaseException;
import org.junit.jupiter.api.Test;

import static com.osa.training.validator.impl.ValidatorTestHelper.failIfInvalid;
import static com.osa.training.validator.impl.ValidatorTestHelper.mockHighValuePurchase;
import static com.osa.training.validator.impl.ValidatorTestHelper.mockLowValuePurchase;
import static com.osa.training.validator.impl.ValidatorTestHelper.mockPurchaseWithStatus;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoyaltyValidatorTest {

    private Validator validator = new LoyaltyValidator();

    @Test
    void shouldPassExperiencedCustomerWithLowValuePurchase() {
        CustomerDTO customer = CustomerDTO.builder()
                .purchases(ImmutableSet.of(
                        mockPurchaseWithStatus(Status.COMPLETED),
                        mockPurchaseWithStatus(Status.COMPLETED),
                        mockPurchaseWithStatus(Status.COMPLETED)))
                .build();
        PurchaseDTO purchase = mockLowValuePurchase();

        failIfInvalid(validator, customer, purchase);
    }

    @Test
    void shouldPassExperiencedCustomerWithHighValuePurchase() {
        CustomerDTO customer = CustomerDTO.builder()
                .purchases(ImmutableSet.of(
                        mockPurchaseWithStatus(Status.COMPLETED),
                        mockPurchaseWithStatus(Status.COMPLETED),
                        mockPurchaseWithStatus(Status.COMPLETED)))
                .build();
        PurchaseDTO purchase = mockHighValuePurchase();

        failIfInvalid(validator, customer, purchase);
    }

    @Test
    void shouldPassRookieCustomerWithLowValuePurchase() {
        CustomerDTO customer = CustomerDTO.builder()
                .purchases(ImmutableSet.of(
                        mockPurchaseWithStatus(Status.COMPLETED),
                        mockPurchaseWithStatus(Status.COMPLETED),
                        mockPurchaseWithStatus(Status.IN_DELIVERY)))
                .build();
        PurchaseDTO purchase = mockLowValuePurchase();

        failIfInvalid(validator, customer, purchase);
    }

    @Test
    void shouldFailRookieCustomerWithHighValuePurchase() {
        CustomerDTO customer = CustomerDTO.builder()
                .purchases(ImmutableSet.of(
                        mockPurchaseWithStatus(Status.COMPLETED),
                        mockPurchaseWithStatus(Status.COMPLETED)))
                .build();
        PurchaseDTO purchase = mockHighValuePurchase();
        assertThrows(InvalidPurchaseException.class, () -> validator.validate(customer, purchase));
    }

}
