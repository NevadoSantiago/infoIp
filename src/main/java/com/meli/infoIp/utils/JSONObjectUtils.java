package com.meli.infoIp.utils;

import java.util.Iterator;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectUtils {

    public static String getValueOfJsonByKey(JSONObject json, String key) throws JSONException {
        return (String)json.get(key);
    }
    public static Optional<String> getValueOfJsonByPosition(JSONObject json, int position) throws JSONException {
        Iterator iterator = json.keys();
        int count = 0;
        while(iterator.hasNext() && count<=position ){
            String key = (String)iterator.next();
            if(count == position) return Optional.of(getValueOfJsonByKey(json,key));
        }
        return Optional.empty();
    }
}
