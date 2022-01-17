package com.meli.infoIp.services;

import com.google.gson.Gson;
import com.meli.infoIp.model.apiCall.CurrenciesResponse;
import com.meli.infoIp.model.apiCall.InfoCountryResponse;
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

    @Value("${infoCurrencieDomain}")
    private String infoCurrencieDomain;

    @Value("${apiCurrencieKey}")
    private String apiKey;

    public JSONObject getInfoIp(String ipAddress){
        log.info("Getting info of: {}", ipAddress);
        return getApiInfo(infoIpDomain, Optional.of(ipAddress), Boolean.FALSE);
    }
    public InfoCountryResponse getInfoCountryByName(String countryName) throws Exception {
        log.info("Getting info of: {}", countryName);
        JSONObject json = getApiInfo(infoCountryDomain,Optional.of(countryName), Boolean.TRUE);
        Optional<InfoCountryResponse> optionalResponse =
            InfoCountryResponse.mapFromJsonObject(json);
        if(optionalResponse.isEmpty()) throw new Exception("Error during get info country response");
        return optionalResponse.get() ;
    }
    public CurrenciesResponse getActualCurrencieInformation() throws Exception {
        log.info("Getting info of actual currencies");
        JSONObject json = getApiInfo(infoCurrencieDomain,Optional.empty(), Boolean.FALSE);
        Optional<CurrenciesResponse> optionalResponse =
            CurrenciesResponse.mapFromJsonObject(json);
        if(optionalResponse.isEmpty()) throw new Exception("Error during get info of currencies");
        return optionalResponse.get();
    }

    public Double getActualPriceOfCurrencie(String currencie){
        return null;
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
