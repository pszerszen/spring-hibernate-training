package com.osa.training.validator.impl;

import com.osa.training.model.dto.CustomerDTO;
import com.osa.training.model.dto.OrderProductDTO;
import com.osa.training.model.dto.ProductDTO;
import com.osa.training.model.dto.PurchaseDTO;
import com.osa.training.validator.Validator;
import com.osa.training.validator.exception.InvalidPurchaseException;
import org.springframework.stereotype.Component;

@Component
public class CargoValueValidator implements Validator {

    static final int MAX_AMOUNT_OF_VALUABLE_PRODUCTS = 5;
    static final int VALUABLE_PRODUCT_PRICE_THRESHOLD = 7000;

    @Override
    public void validate(CustomerDTO customer, PurchaseDTO purchase) {
        boolean invalidPurchase = purchase.getOrders().stream().anyMatch(this::isOrderInvalid);

        if (invalidPurchase) {
            String errorMessage = String.format("Purchase cannot have more than %s same products with unit Price bigger than %s PLN each.",
                    MAX_AMOUNT_OF_VALUABLE_PRODUCTS,
                    VALUABLE_PRODUCT_PRICE_THRESHOLD);
            throw new InvalidPurchaseException(errorMessage);
        }
    }

    private boolean isOrderInvalid(OrderProductDTO order) {
        return isAmountOfValuableProductsTooBig(order) && isProductValuable(order.getProduct());
    }

    private boolean isAmountOfValuableProductsTooBig(OrderProductDTO order) {
        return order.getAmount() > MAX_AMOUNT_OF_VALUABLE_PRODUCTS;
    }

    private boolean isProductValuable(ProductDTO product) {
        return product.getPrice().doubleValue() > VALUABLE_PRODUCT_PRICE_THRESHOLD;
    }
}
