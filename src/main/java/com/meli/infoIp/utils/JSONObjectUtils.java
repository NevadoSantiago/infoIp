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
            count++;
        }
        return Optional.empty();
    }
    public static void showKeysOfJsonObject(JSONObject json){
        Integer count = 0;
        Iterator keys = json.keys();
        while(keys.hasNext()){
            System.out.println(count + "--" + (String)keys.next());
            count++;
        }
    }
    public static void showAllValues(JSONObject json, Integer cantidad) throws JSONException {
        Integer count = 0;
        Iterator keys = json.keys();
        while(keys.hasNext() && count <=cantidad){
            try{
                System.out.println(count + "--" + getValueOfJsonByPosition(json,count).get());
            }catch(Exception e){
                e.getMessage();
            }
            count++;
        }
    }
}
