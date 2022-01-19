package com.meli.infoIp.utils;

import com.meli.infoIp.exceptions.JsonReadingException;
import org.json.JSONObject;

public class JSONObjectUtils {

    private final static String READING_JSON_STRING_CAUSE = "Error durante la lectura del json clave= ";
    private final static String READING_JSON_STRING_ACTION = "Verifique el estad del Json";


    public static String getValueOfJsonByKey(JSONObject json, String key) {
        try{
            return (String)json.get(key);
        }catch(Exception e){
            throw new
                JsonReadingException(READING_JSON_STRING_CAUSE + key,READING_JSON_STRING_ACTION);
        }
    }
}
