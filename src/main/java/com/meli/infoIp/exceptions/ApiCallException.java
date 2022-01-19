package com.meli.infoIp.exceptions;

import com.meli.infoIp.model.ErrorResponse;
import org.springframework.http.HttpStatus;

public class ApiCallException extends ApiException{

    public ApiCallException(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR,
            ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Error durante la llamada a una api")
                .cause(msg)
                .build()
        );
    }

}
