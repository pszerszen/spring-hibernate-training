package com.osa.training.validator.exception;

public class InvalidPurchaseException extends RuntimeException {

    private static final long serialVersionUID = -7226142734735521487L;

    public InvalidPurchaseException(){
    }

    public InvalidPurchaseException(String message) {
        super(message);
    }
}

