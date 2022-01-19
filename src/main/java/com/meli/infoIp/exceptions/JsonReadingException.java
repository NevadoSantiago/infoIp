package com.meli.infoIp.exceptions;

import com.meli.infoIp.model.ErrorResponse;
import org.springframework.http.HttpStatus;

public class JsonReadingException extends ApiException {

    public JsonReadingException(String cause, String action) {
        super(HttpStatus.INTERNAL_SERVER_ERROR,
            ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Error durante la lectura del JSON")
                .cause(cause)
                .action(action)
                .build()
        );
    }
}
