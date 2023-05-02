/*
 * Cinnamon proprietary and confidential.
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023 Cinnamon.
 * All rights reserved.
 */
package com.example.cinnamon.input.rest.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;


@JsonInclude(value = Include.NON_EMPTY)
@Schema(title = "Error")
public class ErrorResponse
{
    @Schema(title = "Sub-status code specific to this error", example = "1")
    private Integer subStatusCode;
    @Schema(title = "Description of error.", example = "Invalid argument in request")
    private String message;
    @Schema(title = "The url to more detailed documentation for this error.", example = "https://help.cinnamon.com")
    private String documentationUrl;

    public ErrorResponse()
    {
    }

    public ErrorResponse(String message)
    {
        this(null, message, null);
    }

    public ErrorResponse(Integer subStatusCode, String message, String documentationUrl)
    {
        this.subStatusCode = subStatusCode;
        this.message = message;
        this.documentationUrl = documentationUrl;
    }

    public Integer getSubStatusCode()
    {
        return subStatusCode;
    }

    public ErrorResponse setSubStatusCode(Integer subStatusCode)
    {
        this.subStatusCode = subStatusCode;
        return this;
    }

    public String getMessage()
    {
        return message;
    }

    public ErrorResponse setMessage(String message)
    {
        this.message = message;
        return this;
    }

    public String getDocumentationUrl()
    {
        return documentationUrl;
    }

    public ErrorResponse setDocumentationUrl(String documentationUrl)
    {
        this.documentationUrl = documentationUrl;
        return this;
    }
}
