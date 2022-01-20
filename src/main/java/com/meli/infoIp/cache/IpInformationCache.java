package com.meli.infoIp.cache;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IpInformationCache {

    private Map<String, JSONObject> informationCache;

    public IpInformationCache(){
        informationCache = new HashMap<>();
    }

    public void add(String key, JSONObject info){

        informationCache.putIfAbsent(key, info);
    }
    public JSONObject get(String key){
        return  informationCache.getOrDefault(key, null);
    }

}
