package com.meli.infoIp.services;

import com.google.gson.Gson;
import com.meli.infoIp.model.InfoIpResponse;
import com.meli.infoIp.model.apiCall.CurrenciesResponse;
import com.meli.infoIp.model.apiCall.InfoCountryResponse;
import com.meli.infoIp.model.entity.Invocation;
import com.meli.infoIp.services.interfaces.InfoIpInterface;
import com.meli.infoIp.utils.JSONObjectUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InfoIpService implements InfoIpInterface {

    private final String JSON_ATTRIBUTE_COUNTRY_NAME = "countryName";

    @Autowired
    ApiCalls apiCallsService;

    @Autowired
    InvocationService invocationService;

    public InfoIpResponse getInfoByIpAddress(String ipAddress) throws Exception {
        log.info("Starting data collection of {}",ipAddress);
        JSONObject ipInfo = apiCallsService.getInfoIp(ipAddress);
    InfoCountryResponse infoCountryResponse = apiCallsService.getInfoCountryByName(
            JSONObjectUtils.getValueOfJsonByKey(ipInfo,JSON_ATTRIBUTE_COUNTRY_NAME)
        );
    CurrenciesResponse infoCurrencyResponse = apiCallsService.getActualCurrencieInformation();
    return InfoIpResponse.buildResponseByInformation(
        infoCurrencyResponse,
        infoCountryResponse
    );
    }

    private InfoCountryResponse getInfoCountryByName(String countryName){

        return null;
    }
}
