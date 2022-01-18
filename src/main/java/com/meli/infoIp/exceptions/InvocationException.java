package com.meli.infoIp.exceptions;

import com.meli.infoIp.model.ErrorResponse;
import org.springframework.http.HttpStatus;

public class InvocationException extends ApiException {

    public InvocationException(String cause, String action) {
        super(HttpStatus.BAD_REQUEST,
                ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Reading invocations exception")
                .cause(cause)
                .action(action)
                    .build()
        );
    }
    public Object getLastError(){
        return this.getStackTrace()[0];
    }
}
