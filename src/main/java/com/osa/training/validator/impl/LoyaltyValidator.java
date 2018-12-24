package com.osa.training.validator.impl;

import com.osa.training.model.dto.CustomerDTO;
import com.osa.training.model.dto.OrderProductDTO;
import com.osa.training.model.dto.ProductDTO;
import com.osa.training.model.dto.PurchaseDTO;
import com.osa.training.model.persistance.Status;
import com.osa.training.validator.Validator;
import com.osa.training.validator.exception.InvalidPurchaseException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Check is customer who has less than {@link #MIN_AMOUNT_OF_COMPLETED_PURCHASES_FOR_EXPERIENCED_CUSTOMER} Purchases having {@link
 * com.osa.training.model.persistance.Status} COMPLETED is not trying to buy {@link #MAX_PURCHASE_VALUE_FOR_NEW_CUSTOMERS} PLN worth of cargo.
 */
@Component
public class LoyaltyValidator implements Validator {

    private static final BigDecimal PERCENT_MULTIPLIER = BigDecimal.valueOf(0.01);

    private static final int MIN_AMOUNT_OF_COMPLETED_PURCHASES_FOR_EXPERIENCED_CUSTOMER = 3;
    private static final int MAX_PURCHASE_VALUE_FOR_NEW_CUSTOMERS = 5_000;

    @Override
    public void validate(CustomerDTO customer, PurchaseDTO purchase) {
        if (!(isCustomerExperienced(customer) || isTotalPurchaseValueSmallEnough(purchase))) {
            String errorMessage = String.format("Customer that has less than %s completed purchases cannot order over %s zł.",
                    MAX_PURCHASE_VALUE_FOR_NEW_CUSTOMERS,
                    MIN_AMOUNT_OF_COMPLETED_PURCHASES_FOR_EXPERIENCED_CUSTOMER);
            throw new InvalidPurchaseException(errorMessage);
        }
    }

    private boolean isCustomerExperienced(CustomerDTO customer) {
        long completedPurchases = customer.getPurchases().stream()
                .map(PurchaseDTO::getStatus)
                .filter(status -> status == Status.COMPLETED)
                .count();

        return completedPurchases >= MIN_AMOUNT_OF_COMPLETED_PURCHASES_FOR_EXPERIENCED_CUSTOMER;
    }

    private boolean isTotalPurchaseValueSmallEnough(PurchaseDTO purchase) {
        BigDecimal totalPurchaseGrossValue = purchase.getOrders().stream()
                .map(this::getOrderValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPurchaseGrossValue.doubleValue() <= MAX_PURCHASE_VALUE_FOR_NEW_CUSTOMERS;
    }

    private BigDecimal getOrderValue(OrderProductDTO order) {
        ProductDTO product = order.getProduct();
        BigDecimal nettoPrice = product.getPrice();
        BigDecimal grossMultiplier = BigDecimal.valueOf(100)
                .add(product.getMarge())
                .multiply(PERCENT_MULTIPLIER);

        return nettoPrice.multiply(grossMultiplier)
                .multiply(BigDecimal.valueOf(order.getAmount()));
    }
}
