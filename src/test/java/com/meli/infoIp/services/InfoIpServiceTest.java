package com.meli.infoIp.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.meli.infoIp.exceptions.IllegalArgumentException;
import com.meli.infoIp.model.InfoIpResponse;
import com.meli.infoIp.model.apiCall.CurrenciesResponse;
import com.meli.infoIp.model.apiCall.InfoCountryResponse;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InfoIpServiceTest {

    private final Double RELATION_USD_EUR = 1.2;
    private final Double RELATION_ARS_EUR = 120.0;

    InfoIpService infoIpService;

    @Mock
    InvocationService invocationService;
    @Mock
    ApiCalls apiCallsService;

    @BeforeEach
    public void init(){

         final String infoCountryDomain = "https://restcountries.com/v3.1/name/";
         final String infoIpDomain = "https://api.ip2country.info/ip?";
         final String infoCurrencieDomain = "http://data.fixer.io/api/latest?access_key=";
         final String apiKey = "8edbb3ca887ac69bc48685ce4e80309c";

        MockitoAnnotations.initMocks(this);
        infoIpService = new InfoIpService(apiCallsService,invocationService);
    }

    @Test
    public void testCorrectBuildResponse() throws Exception {
        String ip = "127.0.0.1";
        InfoCountryResponse countryResponse = buildValidInfoCountryResponse();
        CurrenciesResponse currencyResponse = buildSpecificCurrencyResponseByCurrency("ARS");
        when(apiCallsService.getCountryNameByIpAddress(ip))
            .thenReturn("Argentina");
        when(apiCallsService.getInfoCountryByName("Argentina"))
            .thenReturn(countryResponse);
        when(apiCallsService.getActualCurrencieInformation())
            .thenReturn(currencyResponse);

        InfoIpResponse response = infoIpService.getInfoByIpAddress(ip);
        assertionsResponses(countryResponse,response);
    }

    @Test
    public void IllegalArgumentExceptionIfIpIsNull() {
        assertThrows(IllegalArgumentException.class,() ->
            infoIpService.getInfoByIpAddress(null));
    }

    public InfoCountryResponse buildValidInfoCountryResponse(){
        Map<String,Object> currencies = new HashMap<>();
        currencies.put("ARS",null);
        Map<String,String> languages = new HashMap<>();
        currencies.put("spa","Spanish");
        return InfoCountryResponse.builder()
            .countryName("Argentina")
            .cioc("ARG")
            .currencies(currencies)
            .languages(languages)
            .timezones(new String[] {"UTC-03:00"})
            .latlng(new String[]{"-34.0","-64.0"})
            .build();
    }
    public CurrenciesResponse buildSpecificCurrencyResponseByCurrency(String currency){
        Map<String,Double> rates = new HashMap<>();
        rates.put("ARS", 120.0);
        rates.put("USD", 1.2);
        return CurrenciesResponse.builder()
            .base("EUR")
            .rates(rates)
            .build();
    }
    public void assertionsResponses(
        InfoCountryResponse infoCountryResponse,
        InfoIpResponse infoIpResponse
    ){
        assertThat(infoIpResponse.getCoinInfo().getActualPrice()).isEqualTo(
                RELATION_ARS_EUR/RELATION_USD_EUR
        );
        assertThat(infoIpResponse.getCountryName())
            .isEqualToIgnoringCase(infoCountryResponse.getCountryName());
        assertThat(infoIpResponse.getIsoCode()).isEqualTo(infoCountryResponse.getCioc());
        assertThat(infoIpResponse.getOfficialLanguage()).isEqualTo(infoCountryResponse.getLanguages());


    }
}
