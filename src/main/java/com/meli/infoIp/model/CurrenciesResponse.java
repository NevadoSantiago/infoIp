package com.meli.infoIp.model;

import com.google.gson.Gson;
import com.meli.infoIp.model.apiCall.InfoCountryResponse;
import java.util.Optional;
import lombok.Data;
import org.json.JSONObject;

@Data
public class CurrenciesResponse {

    private String name;
    private String symbol;


}
