package com.meli.infoIp.utils;

import com.meli.infoIp.exceptions.IllegalArgumentException;

public class StringUtils {

    public static void validateStringIsPresent(String value, String errorMsg){
        if(value == null || value.isBlank() || value.isEmpty())
            throw new IllegalArgumentException(errorMsg);
    }
}
