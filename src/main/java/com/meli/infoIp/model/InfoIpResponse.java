package com.meli.infoIp.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
@Builder
public class InfoIpResponse {

    private String countryName;
    private String isoCode;
    private List<String> officialLanguage;
    private List<LocalDateTime> localDateTimes;
    private Integer estimatedDistance;
    private CoinInfoResponse coinInfo;

    private InfoIpResponse extractAndMapInfoFromJson(JSONObject json){

        InfoIpResponse response = InfoIpResponse.builder().build();
        return null;
    }

}




