package com.meli.infoIp.exceptions;

import com.meli.infoIp.model.ErrorResponse;
import org.springframework.http.HttpStatus;

public class IllegalArgumentException extends ApiException {

    public IllegalArgumentException(String msg) {
        super(HttpStatus.BAD_REQUEST,
            ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Error en un parametro")
                .cause(msg)
                .build()
        );
    }
}
