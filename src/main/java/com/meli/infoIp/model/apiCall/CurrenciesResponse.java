package com.meli.infoIp.model.apiCall;

import com.google.gson.Gson;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CurrenciesResponse {

    private String base;
    private Map<String, Double> rates;

    public static Optional<CurrenciesResponse> mapFromJsonObject(JSONObject json) {
        Gson gson = new Gson();
        try {
            return Optional.of(gson.fromJson(
                json.toString(),
                CurrenciesResponse.class));
        } catch (Exception e) {
            log.error("An error was occurred trying map json to InfoCountryResponse");
            return Optional.empty();
        }
    }
    public Double getActualPriceInUsdOf(String currency){
        Double relationUSDToEUR =  rates.get("USD");
        if(base.equalsIgnoreCase(currency)) return relationUSDToEUR;
        return rates.get(currency)/relationUSDToEUR;
    }
}
