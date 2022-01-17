package com.meli.infoIp.model;

import com.meli.infoIp.model.apiCall.CurrenciesResponse;
import com.meli.infoIp.model.apiCall.InfoCountryResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
@Setter
@Builder
public class InfoIpResponse {

    private static final String COMMON_NAME_KEY = "common";

    private String countryName;
    private String isoCode;
    private Map<String,String> officialLanguage;
    private List<LocalDateTime> localDateTimes;
    private Integer estimatedDistance;
    private CoinInfoResponse coinInfo;

    public static InfoIpResponse buildResponseByInformation(
        CurrenciesResponse currency,
        InfoCountryResponse country
    ) throws JSONException {
        return InfoIpResponse.builder()
            .countryName(country.getCountryName())
            .coinInfo(new CoinInfoResponse(
                country.getCurrencyName(),
                currency.getActualPriceInUsdOf(country.getCurrencyName())
            ))
            .officialLanguage(country.getLanguages())
            .isoCode(country.getCioc())
            .build();
    }

}




