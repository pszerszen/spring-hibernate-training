package com.osa.training.validator;

import com.osa.training.model.dto.CustomerDTO;
import com.osa.training.model.dto.PurchaseDTO;

public interface Validator {

    void validate(CustomerDTO customer, PurchaseDTO purchase);
}
