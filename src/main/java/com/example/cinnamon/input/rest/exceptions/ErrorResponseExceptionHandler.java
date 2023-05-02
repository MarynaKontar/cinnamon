/*
 * Cinnamon proprietary and confidential.
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023 Cinnamon.
 * All rights reserved.
 */
package com.example.cinnamon.input.rest.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorResponseExceptionHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorResponseExceptionHandler.class);

    @ExceptionHandler(value = { ErrorResponseException.class })
    protected ResponseEntity<ErrorResponse> handleErrorResponseExceptions(RuntimeException ex, WebRequest request)
    {
        ErrorResponseException ere = (ErrorResponseException) ex;

        LOGGER.debug(ex.getMessage(), ex);
        if (ere.hasHeaders())
        {
            return ResponseEntity.status(ere.getStatusCode()).headers(ere.getResponseHeaders()).body(ere.getErrorResponse());
        }
        return ResponseEntity.status(ere.getStatusCode()).body(ere.getErrorResponse());
    }

    /**
     * Handles BindException when validating dtos
     */
    @ExceptionHandler(value = { BindException.class })
    protected ResponseEntity<ErrorResponse> handleBindExceptions(Exception ex, WebRequest request)
    {
        final BindException be = (BindException) ex;
        final String message = be.getFieldError() != null ? be.getFieldError().getDefaultMessage()
                : be.getGlobalError() != null ? be.getGlobalError().getDefaultMessage() : "parameter is not valid";

        final ErrorResponse errorResponse = new ErrorResponse(message);

        LOGGER.debug(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}