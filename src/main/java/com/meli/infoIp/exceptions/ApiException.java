package com.meli.infoIp.exceptions;

import com.meli.infoIp.model.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApiException extends RuntimeException {

    private HttpStatus httpStatus;

    private ErrorResponse errorResponse;

    ApiException(HttpStatus httpStatus, ErrorResponse errorResponse) {
        super(String.format("%s, %s", errorResponse.getTitle(), errorResponse.getCause()));
        this.httpStatus = httpStatus;
        this.errorResponse = errorResponse;
    }
}
