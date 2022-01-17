package com.meli.infoIp.services;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApiCalls {

    @Value("${infoCountryDomain}")
    private String infoCountryDomain;

    @Value("${infoIpDomain}")
    private String infoIpDomain;

    public JSONObject getInfoIp(String ipAddress){
        log.info("Getting info of: {}", ipAddress);
        return getApiInfo(infoIpDomain, Optional.of(ipAddress), Boolean.FALSE);
    }
    public JSONObject getInfoCountryByName(String countryName){
        log.info("Getting info of: {}", countryName);
        return getApiInfo(infoCountryDomain,Optional.of(countryName), Boolean.TRUE);
    }

    public JSONObject getApiInfo(
        String url,
        Optional<String> parameter,
        Boolean isJsonArray) {
        JSONObject json = null;
        if (parameter.isPresent()) url = url + parameter.get();
        final String finalUrl = url;
        try {
            URL urlObject = new URL(finalUrl);
            HttpURLConnection con;
            con = (HttpURLConnection) urlObject.openConnection();
            StringBuilder sb = new StringBuilder();
            String line;
            con.setRequestMethod("GET");
            con.connect();
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(con.getInputStream()));
            //BTCInfoRequest info = gson.fromJson(reader.readLine(), BTCInfoRequest.class);
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            if(isJsonArray){
                JSONArray jsonArray = new JSONArray(sb.toString());
                json = (JSONObject)jsonArray.get(0);
            }else json = new JSONObject(sb.toString());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
