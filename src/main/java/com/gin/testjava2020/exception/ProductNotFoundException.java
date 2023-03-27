package com.gin.testjava2020.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Product not found exception.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product Not found for the required date-time")
public class ProductNotFoundException extends Exception{

    /**
     * Instantiates a new Product not found exception.
     *
     * @param message the message
     */
    public ProductNotFoundException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Product not found exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
