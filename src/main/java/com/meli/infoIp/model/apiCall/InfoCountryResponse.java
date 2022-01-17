package com.meli.infoIp.model.apiCall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

@Getter
@Setter
@Slf4j
public class InfoCountryResponse {

    private String cioc;
    private String[] timezones;
    private JSONObject name;
    private String[] latlng;
    Map<String, Object> currencies;

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

        /*private String[] capital;
    private String flag;
    private Boolean independent;
    private Boolean landlocked;
    private List<String> borders;
    private JSONObject postalCode;
    private JSONObject flags;
    private JSONObject capitalInfo;
    private String ccn3;
    private JSONObject coatOfArms;
    private JSONObject demonyms;
    private String fifa;*/
        /*private String cca3;
    private String region;*/
        /*private String cca2;
    private String status;*/
        /*private JSONObject car;
    private JSONObject translations;
    private JSONArray altSpellings;
    private Double area;
    private JSONObject languages;
    private JSONObject maps;
    private String subregion;
    private JSONObject idd;
    private JSONArray tld;
    private Boolean unMember;
    private JSONObject gini;
    private JSONArray continents;
    private Integer population;
    private String startOfWeek;*/

    

}



