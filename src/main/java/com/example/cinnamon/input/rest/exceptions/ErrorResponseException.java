package com.example.cinnamon.input.rest.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ErrorResponseException extends ResponseStatusException
{
    private static final long serialVersionUID = -2150138175811122469L;
    private ErrorResponse errorResponse;
    private HttpHeaders headers;

    public ErrorResponseException(final HttpStatus status)
    {
        this(status, null);
    }

    public ErrorResponseException(HttpStatus status, String reason)
    {
        this(status, new ErrorResponse(reason), null);
    }

    public ErrorResponseException(HttpStatus status, String reason, Throwable cause)
    {
        this(status, new ErrorResponse(reason), cause);
    }

    public ErrorResponseException(HttpStatus status, ErrorResponse errorResponse, Throwable cause)
    {
        super(status, errorResponse.getMessage(), cause);
        this.errorResponse = errorResponse;
    }

    public ErrorResponseException(HttpStatus status, String headerKey, String headerValue)
    {
        super(status);
        headers = new HttpHeaders();
        headers.add(headerKey, headerValue);
    }

    public ErrorResponse getErrorResponse()
    {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse)
    {
        this.errorResponse = errorResponse;
    }

    @Override
    public HttpHeaders getHeaders()
    {
        return headers;
    }

    public boolean hasHeaders()
    {
        return headers != null && headers.size() > 0;
    }
}
