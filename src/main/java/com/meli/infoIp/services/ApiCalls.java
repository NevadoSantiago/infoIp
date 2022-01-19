package com.meli.infoIp.services;

import com.meli.infoIp.cache.IpInformationCache;
import com.meli.infoIp.exceptions.ApiCallException;
import com.meli.infoIp.model.apiCall.CurrenciesResponse;
import com.meli.infoIp.model.apiCall.InfoCountryResponse;
import com.meli.infoIp.utils.JSONObjectUtils;
import com.meli.infoIp.utils.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiCalls {

    @Autowired
    IpInformationCache cache;



    private final String JSON_ATTRIBUTE_COUNTRY_NAME = "countryName";

    public final String COUNTRY_NAME_VALUE_ISNT_PRESENT_ERROR =
        "El nombre del pais no puede ser nulo";
    private final String API_CALL_ERROR =
        "Hubo un error durante la consulta a una api: ";

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
        return getApiInfo(
            infoIpDomain,
            Optional.of(ipAddress),
            Boolean.FALSE,
            Boolean.TRUE);
    }
    public String getCountryNameByIpAddress(String ipAddress){
        return JSONObjectUtils.getValueOfJsonByKey(
            getInfoIp(ipAddress),
            JSON_ATTRIBUTE_COUNTRY_NAME);
    }

    public InfoCountryResponse getInfoCountryByName(String countryName) throws Exception {
        StringUtils.validateStringIsPresent(
            countryName,
            COUNTRY_NAME_VALUE_ISNT_PRESENT_ERROR + " getInfoCountryByName method");

        log.info("Getting info of: {}", countryName);
        JSONObject json = getApiInfo(
            infoCountryDomain,
            Optional.of(countryName),
            Boolean.TRUE,
            Boolean.TRUE);
        Optional<InfoCountryResponse> optionalResponse =
            InfoCountryResponse.mapFromJsonObject(json);

        if(optionalResponse.isEmpty()) throw new Exception("Error during get info country response");

        InfoCountryResponse response = optionalResponse.get();
        response.setCountryName(countryName);
        return response;
    }
    public CurrenciesResponse getActualCurrencieInformation() throws Exception {
        log.info("Getting info of actual currencies");
        JSONObject json = getApiInfo(
            infoCurrencieDomain,
            Optional.of(apiKey),
            Boolean.FALSE,
            Boolean.FALSE);
        Optional<CurrenciesResponse> optionalResponse =
            CurrenciesResponse.mapFromJsonObject(json);
        if(optionalResponse.isEmpty()) throw new Exception("Error during get info of currencies");
        return optionalResponse.get();
    }

    public JSONObject getApiInfo(
        String url,
        Optional<String> parameter,
        Boolean isJsonArray,
        Boolean cacheable) {
        JSONObject json;
        if (parameter.isPresent()) url = url + parameter.get();
        final String finalUrl = url;
        if(cacheable){
            json = cache.get(url);
            if(json!=null) return json;
        }
        try {
            log.info("Consulting.. {} ", url);
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
            if(cacheable) cache.add(url,json);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new ApiCallException(
                API_CALL_ERROR + url
            );
        }
        return json;
    }


}
