package com.meli.infoIp.model.apiCall;

import com.google.gson.Gson;
import java.util.Map;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

@Getter
@Setter
@Slf4j
@Builder
public class InfoCountryResponse {

    private String cioc;
    private String[] timezones;
    private String countryName;
    private String[] latlng;
    private Map<String,String> languages;
    private Map<String, Object> currencies;

    public static Optional<InfoCountryResponse> mapFromJsonObject(JSONObject json){
        Gson gson=new Gson();
        try{
            return Optional.of(gson.fromJson(
                json.toString(),
                InfoCountryResponse.class));
        }catch(Exception e){
            log.error("An error was occurred trying map json to InfoCountryResponse");
            return Optional.empty();
        }

    }
    public String getCurrencyName(){
        Optional<String> opt = this.currencies.keySet().stream().findFirst();
        if(opt.isPresent())return opt.get();
        else return "Invalid currency";
    }
}



