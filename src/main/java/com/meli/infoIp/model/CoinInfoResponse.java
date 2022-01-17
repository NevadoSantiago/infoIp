package com.meli.infoIp.model;

import com.meli.infoIp.model.apiCall.CurrenciesResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CoinInfoResponse {

    private String coinName;
    private Double actualPrice;

    public static CoinInfoResponse buildCoinInfoResponseByCurrencyResponse(
        CurrenciesResponse response,
        String coinName){
        return CoinInfoResponse.builder()
            .actualPrice(response.getActualPriceInUsdOf(coinName))
            .coinName(coinName)
            .build();
    }
}
