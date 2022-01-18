package com.meli.infoIp.model;

import com.meli.infoIp.model.apiCall.CurrenciesResponse;
import com.meli.infoIp.model.apiCall.InfoCountryResponse;
import com.meli.infoIp.utils.DateUtils;
import com.meli.infoIp.utils.GeoDistanceUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@Builder
public class InfoIpResponse {

    private static final String COMMON_NAME_KEY = "common";
    private static Double bsAsLat = -34.58;
    private static Double bsAsLon = -58.67;

    private String countryName;
    private String isoCode;
    private Map<String,String> officialLanguage;
    private List<LocalDateTime> localDateTimes;
    private Double estimatedDistance;
    private CoinInfoResponse coinInfo;

    public static InfoIpResponse buildResponseByInformation(
        CurrenciesResponse currency,
        InfoCountryResponse country
    ) {
        return InfoIpResponse.builder()
            .countryName(country.getCountryName())
            .coinInfo(new CoinInfoResponse(
                country.getCurrencyName(),
                currency.getActualPriceInUsdOf(country.getCurrencyName())
            ))
            .estimatedDistance(GeoDistanceUtil.distance(
                Double.parseDouble(country.getLatlng()[0]),
                bsAsLat,
                Double.parseDouble(country.getLatlng()[1]),
                bsAsLon
            ))
            .localDateTimes(DateUtils.getLocalDateTimeListFromUTCArray(country.getTimezones()))
            .officialLanguage(country.getLanguages())
            .isoCode(country.getCioc())
            .build();
    }

}




